package io.miragon.zeebe.tm.payment.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.payment.application.port.`in`.SendPaymentReceivedUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.SendPaymentReceivedUseCase.Command
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class PaymentReceivedWorker(
    private val useCase: SendPaymentReceivedUseCase
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "payment-received-request")
    fun paymentReceived(@Variable invoiceId: String)
    {
        log.info { "Task \"payment-received\" activated." }
        val command = Command(invoiceId)
        useCase.send(command)
    }
}
