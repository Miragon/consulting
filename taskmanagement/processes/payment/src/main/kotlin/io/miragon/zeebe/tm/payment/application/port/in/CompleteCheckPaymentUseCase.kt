package io.miragon.zeebe.tm.payment.application.port.`in`

interface CompleteCheckPaymentUseCase
{
    fun complete(command: Command): Long

    data class Command(
        val taskId: Long,
        val invoiceId: String,
        val isAccepted: Boolean,
    )
}