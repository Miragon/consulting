package io.miragon.zeebe.tm.exporter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.camunda.zeebe.exporter.api.Exporter
import io.camunda.zeebe.exporter.api.context.Context
import io.camunda.zeebe.exporter.api.context.Controller
import io.camunda.zeebe.protocol.record.Record
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.Logger
import java.io.IOException

@Suppress("unused")
class UserTaskExporter : Exporter
{
    private lateinit var log: Logger
    private lateinit var controller: Controller

    private lateinit var client: OkHttpClient
    private val mapper = jacksonObjectMapper()

    private lateinit var baseUrl: String
    private lateinit var config: ExporterConfiguration

    override fun configure(context: Context)
    {
        val filter = RecordFilter()
        context.setFilter(filter)
        this.log = context.logger

        this.config = context
            .configuration
            .instantiate(ExporterConfiguration::class.java)

        this.baseUrl = config.baseUrl

        if (baseUrl.isEmpty())
        {
            log.warn("No URL provided for user task exporter")
        } else
        {
            log.info("User Task Exporter configured with URL: $baseUrl")
        }

        this.client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build()
    }

    override fun open(controller: Controller)
    {
        this.controller = controller
        log.info("User Task Exporter loaded")
    }

    override fun export(record: Record<*>)
    {
        if (record.intent.name() == "CREATING")
        {
            return
        }

        val recordString = mapper.writeValueAsString(record)

        log.info("User Task Exporter Record: $recordString")

        val url = if (record.intent.name() == "CREATED")
        {
            "$baseUrl/rest/${record.valueType.name.lowercase()}/save"
        } else
        {
            "$baseUrl/rest/${record.valueType.name.lowercase()}/update"
        }

        postUserTaskRecord(url, recordString)

        this.controller.updateLastExportedRecordPosition(record.position)
    }

    private fun postUserTaskRecord(url: String, recordString: String)
    {
        val json = "application/json; charset=utf-8".toMediaType()
        val body = recordString.toRequestBody(json)
        val rb = Request.Builder()

        val request = rb.url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: okhttp3.Call, e: IOException)
            {
                log.error("Failed to send user task job event", e)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response)
            {
                response.use {
                    if (!response.isSuccessful)
                    {
                        log.error("Failed to send user task job event: ${response.code}: ${response.message}")
                    }

                    for ((name, value) in response.headers)
                    {
                        log.debug("$name: $value")
                    }

                    log.debug(response.body!!.string())
                }
            }
        })
    }
}
