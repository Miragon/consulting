package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.PaymentReceivedPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class PaymentReceivedMessage(
    private val zeebeClient: ZeebeClient
) : PaymentReceivedPort
{
    private val log = KotlinLogging.logger {}

    private val eventName = "PaymentReceivedEvent"

    override fun correlateMessage(orderId: String, invoiceId: String)
    {
        val variables = mapOf("invoiceId" to invoiceId)
        try
        {
            zeebeClient.newPublishMessageCommand()
                .messageName(eventName)
                .correlationKey(orderId)
                .variables(variables)
                .send()
                .join()
        } catch (e: Exception)
        {
            log.error(e) { "Failed to correlate message $eventName." }
        }
    }
}
