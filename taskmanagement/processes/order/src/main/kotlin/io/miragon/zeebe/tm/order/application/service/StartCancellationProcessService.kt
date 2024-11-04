package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.StartCancellationUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartCancellationUseCase.Query
import io.miragon.zeebe.tm.order.application.port.out.CancelOrderPort
import org.springframework.stereotype.Service

@Service
class StartCancellationProcessService(
    private val cancelOrderPort: CancelOrderPort,
) : StartCancellationUseCase
{
    override fun handle(query: Query)
    {
        val (orderId) = query
        cancelOrderPort.correlateMessage(orderId)
    }
}