package io.miragon.zeebe.tm.payment.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.payment.application.port.`in`.SendCancellationUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.SendCancellationUseCase.Command
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CancelOrderWorker(
    private val useCase: SendCancellationUseCase
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "cancel-order-request")
    fun cancelOrder(@Variable invoiceId: String)
    {
        log.info { "Task \"cancel-order\" activated." }
        useCase.send(Command(invoiceId))
    }
}
