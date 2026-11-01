package io.miragon.zeebe.tm.payment.application.port.`in`

interface SendCancellationUseCase
{
    fun send(command: Command)

    data class Command(
        val invoiceId: String,
    )
}
