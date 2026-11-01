package io.miragon.zeebe.tm.payment.adapter.`in`.kafka

import io.miragon.zeebe.tm.libs.shared.kafka.PaymentRequest
import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase.Command
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentRequestConsumer(
    private val createInvoiceUseCase: CreateInvoiceUseCase
)
{
    private val log = KotlinLogging.logger {}

    @KafkaListener(topics = ["payment-request"], groupId = "payment-process")
    fun consume(request: PaymentRequest)
    {
        log.info { "Request \"payment-request\" received." }
        val (orderId, amount) = request
        createInvoiceUseCase.create(Command(orderId, amount))
    }
}
