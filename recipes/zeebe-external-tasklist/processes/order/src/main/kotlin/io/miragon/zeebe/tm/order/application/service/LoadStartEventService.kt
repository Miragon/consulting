package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.ItemPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.ReadFormPort
import org.springframework.stereotype.Service

@Service
class LoadStartEventService(
    private val itemPersistencePort: ItemPersistencePort,
    private val readFormPort: ReadFormPort,
) : LoadStartEventUseCase
{
    override fun load(command: Command): Response
    {
        val htmlString = readFormPort.read(command.filePath)
        val items = itemPersistencePort.findAll()

        return Response(
            htmlString = htmlString,
            formData = items,
        )
    }
}