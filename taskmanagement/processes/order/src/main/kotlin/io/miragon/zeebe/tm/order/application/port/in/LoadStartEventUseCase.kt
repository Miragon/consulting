package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Item

interface LoadStartEventUseCase
{
    fun load(command: Command): Response

    data class Command(
        val filePath: String,
    )

    data class Response(
        val htmlString: String,
        val formData: List<Item>
    )
}