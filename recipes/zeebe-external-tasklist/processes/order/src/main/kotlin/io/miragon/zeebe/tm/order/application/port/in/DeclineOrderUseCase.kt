package io.miragon.zeebe.tm.order.application.port.`in`

interface DeclineOrderUseCase
{
    /**
     * Declines the order.
     * @return True if the order was declined, false otherwise.
     */
    fun decline(query: Query)

    data class Query(
        val orderId: String,
    )
}