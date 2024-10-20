package io.miragon.zeebe.tm.payment.application.port.`in`

import java.math.BigDecimal

interface CreateInvoiceUseCase
{
    fun create(command: Command)

    data class Command(
        val orderId: String,
        val amount: BigDecimal,
    )
}