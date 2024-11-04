package io.miragon.zeebe.tm.order.application.port.`in`

interface CancelOrderUseCase
{
    fun handle(command: Command)

    data class Command(
        val orderId: String,
    )
}