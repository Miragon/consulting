package io.miragon.zeebe.tm.exporter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.camunda.zeebe.exporter.api.Exporter
import io.camunda.zeebe.exporter.api.context.Context
import io.camunda.zeebe.exporter.api.context.Controller
import io.camunda.zeebe.protocol.record.Record
import io.camunda.zeebe.protocol.record.ValueType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.Logger
import java.io.IOException
import java.time.Duration

/**
 * Forwards user task and variable records to the task manager.
 *
 * Delivery is at-least-once and in log order: each record is sent synchronously and its position is only
 * acknowledged after the task manager answered. If the task manager is unreachable or answers with 5xx,
 * [export] throws and the broker retries the same record with backoff. A 4xx answer means the task manager
 * rejected the record itself; it is logged and skipped, since sending it again cannot succeed.
 */
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
            .connectTimeout(Duration.ofSeconds(2))
            .readTimeout(Duration.ofSeconds(10))
            .build()
    }

    override fun open(controller: Controller)
    {
        this.controller = controller
        log.info("User Task Exporter loaded")
    }

    override fun export(record: Record<*>)
    {
        val action = actionFor(record)
        if (action != null)
        {
            val recordString = mapper.writeValueAsString(record)
            log.info("User Task Exporter Record: $recordString")

            val url = "$baseUrl/rest/${record.valueType.name.lowercase()}/$action"
            postRecord(url, recordString, record)
        }

        this.controller.updateLastExportedRecordPosition(record.position)
    }

    /**
     * Only the intents the task manager handles are sent, all others are acknowledged without a call.
     */
    private fun actionFor(record: Record<*>): String?
    {
        val intent = record.intent.name()
        return when (record.valueType)
        {
            ValueType.USER_TASK -> when (intent)
            {
                "CREATED" -> "save"
                "COMPLETED", "CANCELED" -> "update"
                else -> null
            }

            ValueType.VARIABLE -> when (intent)
            {
                "CREATED" -> "save"
                "UPDATED" -> "update"
                else -> null
            }

            else -> null
        }
    }

    /**
     * Sends the record synchronously. Throws if it has to be sent again, which makes the broker retry it.
     */
    private fun postRecord(url: String, recordString: String, record: Record<*>)
    {
        val json = "application/json; charset=utf-8".toMediaType()
        val request = Request.Builder()
            .url(url)
            .post(recordString.toRequestBody(json))
            .build()

        val description = "${record.valueType} ${record.intent} (position ${record.position}, key ${record.key})"
        try
        {
            client.newCall(request).execute().use { response ->
                when
                {
                    response.isSuccessful -> log.debug("Exported $description")
                    response.code in 400..499 -> log.error(
                        "Task manager rejected $description with ${response.code}, record is skipped: " +
                            response.body?.string()
                    )

                    else -> throw IOException("Task manager answered ${response.code} for $description")
                }
            }
        } catch (e: IOException)
        {
            log.warn("Could not export $description, the broker will retry: ${e.message}")
            throw e
        }
    }
}
