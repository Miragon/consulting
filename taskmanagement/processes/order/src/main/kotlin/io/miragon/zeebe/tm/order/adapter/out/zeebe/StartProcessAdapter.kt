package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.StartProcessPort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StartProcessAdapter(private val zeebeClient: ZeebeClient) : StartProcessPort
{
    @Value("\${order.id}")
    private lateinit var processId: String

    private val log = KotlinLogging.logger {}

    override fun startProcess(orderId: String): Long
    {
        val variables = mapOf("orderId" to orderId)

        try
        {
            val processInstance = zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId(processId)
                .latestVersion()
                .variables(variables)
                .send()
                .join()

            log.info { "Started process $processId for order $orderId." }

            return processInstance.processInstanceKey
        } catch (e: Exception)
        {
            log.error(e) { "Failed to start process $processId." }
            throw e
        }
    }
}