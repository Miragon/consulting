package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.ReadFormPort
import org.springframework.stereotype.Service

@Service
class LoadPrepareOrderTaskService(
    private val readFormPort: ReadFormPort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadPrepareOrderTaskUseCase
{
    override fun load(command: Command): Response
    {
        val (orderId, filePath) = command

        val jsonString = readFormPort.read(filePath)
        val order = orderPersistencePort.findById(orderId)

        return Response(
            jsonString = jsonString,
            items = order.items
        )
    }
}
