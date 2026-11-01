package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.tasklist.FormData
import java.math.BigDecimal

// Data to show in the UI
data class LoadOrder(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    val items: List<CheckOrderItemDto>,
) : FormData

data class CheckOrderItemDto(
    val id: String,
    val name: String,
    val price: BigDecimal,
    val image: String,
    val quantity: Int,
)

// Data that is sent to the application
data class CheckOrder(
    val isOrderValid: Boolean,
) : FormData
