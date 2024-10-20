package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Item

interface LoadPrepareOrderTaskUseCase
{
    fun load(command: Command): Response

    data class Command(
        val orderId: String,
        val filePath: String,
    )

    data class Response(
        val jsonString: String,
        val items: List<Item>,
    )
}