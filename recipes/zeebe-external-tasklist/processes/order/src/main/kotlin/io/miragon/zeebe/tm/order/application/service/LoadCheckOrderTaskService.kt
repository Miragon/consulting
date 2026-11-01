package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.ReadFormPort
import org.springframework.stereotype.Service

@Service
class LoadCheckOrderTaskService(
    private val readFormPort: ReadFormPort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadCheckOrderTaskUseCase
{
    override fun load(command: Command): Response
    {
        val (orderId, filePath) = command

        val htmlString = readFormPort.read(filePath)
        val order = orderPersistencePort.findById(orderId)

        return Response(
            htmlString = htmlString,
            order = order,
        )
    }
}