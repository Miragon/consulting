package io.miragon.zeebe.tm.order.application.port.`in`

interface RequestPaymentUseCase
{
    /**
     * Requests payment for the order.
     * @return The id of the created payment request.
     */
    fun request(command: Command)

    data class Command(
        val orderId: String,
    )
}