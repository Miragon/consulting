package io.miragon.zeebe.tm.libs.shared.kafka

import java.math.BigDecimal

data class PaymentRequest(
    val orderId: String,
    val amount: BigDecimal,
)
