package io.miragon.zeebe.tm.order.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase.Query
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class DeclineOrderWorker(
    private val useCase: DeclineOrderUseCase,
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "decline-order-request")
    fun declineOrder(@Variable orderId: String)
    {
        log.info { "Task \"decline-order\" activated." }
        useCase.decline(Query(orderId))
    }
}
