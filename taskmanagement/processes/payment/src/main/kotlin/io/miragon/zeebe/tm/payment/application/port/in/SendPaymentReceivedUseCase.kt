package io.miragon.zeebe.tm.payment.application.port.`in`

interface SendPaymentReceivedUseCase
{
    fun send(command: Command)

    data class Command(
        val invoiceId: String,
    )
}