package io.miragon.zeebe.tm.libs.shared.kafka

data class PaymentReceivedRequest(
    val invoiceId: String,
    val orderId: String,
)