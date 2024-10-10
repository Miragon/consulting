package io.miragon.zeebe.tm.order.adapter.`in`.kafka

import io.miragon.zeebe.tm.libs.shared.kafka.CancelOrderRequest
import io.miragon.zeebe.tm.order.application.port.`in`.StartCancellationUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartCancellationUseCase.Query
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CancelOrderConsumer(
    private val useCase: StartCancellationUseCase
)
{
    private val log = KotlinLogging.logger {}

    @KafkaListener(topics = ["cancel-order"], groupId = "order-process")
    fun receivePayment(response: CancelOrderRequest)
    {
        log.info { "Request \"cancel-order\" received." }
        val orderId = response.orderId
        useCase.handle(Query(orderId))
    }
}