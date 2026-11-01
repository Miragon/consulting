package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Order

interface LoadCheckOrderTaskUseCase
{
    fun load(command: Command): Response

    data class Command(
        val orderId: String,
        val filePath: String,
    )

    data class Response(
        val htmlString: String,
        val order: Order,
    )
}