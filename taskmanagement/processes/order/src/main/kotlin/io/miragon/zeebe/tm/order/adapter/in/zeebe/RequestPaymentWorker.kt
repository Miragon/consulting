package io.miragon.zeebe.tm.order.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.order.application.port.`in`.RequestPaymentUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.RequestPaymentUseCase.Command
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class RequestPaymentWorker(
    private val useCase: RequestPaymentUseCase
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "receive-payment-request")
    fun requestPayment(@Variable orderId: String)
    {
        log.info { "Task \"request-payment\" activated." }
        val command = Command(orderId)
        useCase.request(command)
    }
}
