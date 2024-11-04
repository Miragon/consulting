package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.CancelOrderPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CancelOrderMessage(
    private val zeebeClient: ZeebeClient
) : CancelOrderPort
{
    private val log = KotlinLogging.logger {}

    private val eventName = "CancelOrderEvent"

    override fun correlateMessage(orderId: String)
    {
        try
        {
            zeebeClient.newPublishMessageCommand()
                .messageName(eventName)
                .correlationKey(orderId)
                .send()
                .join()

            log.info { "Correlated message $eventName for order $orderId." }
        } catch (e: Exception)
        {
            log.error(e) { "Failed to correlate message $eventName." }
        }
    }
}
