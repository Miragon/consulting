package io.miragon.zeebe.tm.order.adapter.`in`.kafka

import io.miragon.zeebe.tm.libs.shared.kafka.PaymentReceivedRequest
import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase.Query
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentReceivedConsumer(
    private val useCase: PaymentReceivedUseCase
)
{
    private val log = KotlinLogging.logger {}

    @KafkaListener(topics = ["payment-received"], groupId = "order-process")
    fun receivePayment(response: PaymentReceivedRequest)
    {
        log.info { "Request \"payment-received\" received." }
        val (invoiceId, orderId) = response
        useCase.handle(Query(orderId, invoiceId))
    }
}
