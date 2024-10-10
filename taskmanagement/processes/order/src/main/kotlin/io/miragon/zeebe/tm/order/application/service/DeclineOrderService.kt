package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase.Query
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.SendEmailPort
import org.springframework.stereotype.Service

@Service
class DeclineOrderService(
    private val orderPersistencePort: OrderPersistencePort,
    private val sendEmailPort: SendEmailPort,
) : DeclineOrderUseCase
{
    override fun decline(query: Query)
    {
        val (orderId) = query
        val order = orderPersistencePort.findById(orderId)

        sendEmailPort.publish(orderId, order.email)
    }
}