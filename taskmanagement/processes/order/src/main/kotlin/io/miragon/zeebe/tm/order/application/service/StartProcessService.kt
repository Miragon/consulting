package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.StartProcessPort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class StartProcessService(
    private val orderPersistencePort: OrderPersistencePort,
    private val startProcessPort: StartProcessPort
) : StartProcessUseCase
{
    override fun startProcess(command: Command): String
    {
        val order = command.order
        order.state = Order.State.CREATED

        val orderId = orderPersistencePort.save(order)
        startProcessPort.startProcess(orderId)

        return orderId
    }
}
