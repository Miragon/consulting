package io.miragon.zeebe.tm.payment.domain

import java.math.BigDecimal

data class Invoice(
    val id: String?, // there is no id if the invoice is not yet persisted
    val orderId: String,
    val amount: BigDecimal,
    var state: State,
)
{
    enum class State
    {
        CREATED,
        ACCEPTED,
        REJECTED
    }
}