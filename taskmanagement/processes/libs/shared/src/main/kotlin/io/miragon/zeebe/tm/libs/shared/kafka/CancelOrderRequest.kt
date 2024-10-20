package io.miragon.zeebe.tm.libs.shared.kafka

data class CancelOrderRequest(
    val orderId: String,
)