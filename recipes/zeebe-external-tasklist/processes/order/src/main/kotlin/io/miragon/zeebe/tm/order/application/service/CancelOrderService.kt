package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.CancelOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CancelOrderUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.SendEmailPort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class CancelOrderService(
    private val orderPersistencePort: OrderPersistencePort,
    private val sendEmailPort: SendEmailPort,
) : CancelOrderUseCase
{
    override fun handle(command: Command)
    {
        val (orderId) = command
        val order = orderPersistencePort.findById(orderId)
        order.state = Order.State.CANCELLED
        orderPersistencePort.update(orderId, order)

        sendEmailPort.publish(orderId, order.email)
    }
}