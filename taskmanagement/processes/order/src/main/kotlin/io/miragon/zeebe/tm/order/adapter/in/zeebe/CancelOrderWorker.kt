package io.miragon.zeebe.tm.order.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.order.application.port.`in`.CancelOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CancelOrderUseCase.Command
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CancelOrderWorker(
    private val useCase: CancelOrderUseCase
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "cancel-order")
    fun cancelOrder(@Variable orderId: String)
    {
        log.info { "Task \"cancel-order\" activated." }
        val command = Command(orderId)
        useCase.handle(command)
    }
}