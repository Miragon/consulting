package io.miragon.zeebe.tm.order.application.port.`in`

interface PaymentReceivedUseCase
{
    /**
     * Processes the payment received event.
     * @return True if the payment was processed, false otherwise.
     */
    fun handle(query: Query)

    data class Query(
        val orderId: String,
        val invoiceId: String,
    )
}