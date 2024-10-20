package io.miragon.zeebe.tm.order.application.port.`in`

interface StartCancellationUseCase
{
    fun handle(query: Query)

    data class Query(
        val orderId: String,
    )
}