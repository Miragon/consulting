package io.miragon.zeebe.tm.payment.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.payment.application.port.out.StartProcessPort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StartProcessAdapter(private val zeebeClient: ZeebeClient) : StartProcessPort
{
    @Value("\${payment.id}")
    private lateinit var processId: String

    private val log = KotlinLogging.logger {}

    override fun startProcess(invoiceId: String): Long
    {
        val variables = mapOf("invoiceId" to invoiceId)

        try
        {
            val processInstance = zeebeClient
                .newCreateInstanceCommand()
                .bpmnProcessId(processId)
                .latestVersion()
                .variables(variables)
                .send()
                .join()

            log.info { "Process instance started with key: ${processInstance.processInstanceKey}" }

            return processInstance.processInstanceKey
        } catch (e: Exception)
        {
            log.error(e) { "Failed to start process." }
            throw e
        }
    }
}
