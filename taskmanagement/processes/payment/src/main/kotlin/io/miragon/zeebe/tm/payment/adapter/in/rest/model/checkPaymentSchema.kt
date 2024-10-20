package io.miragon.zeebe.tm.payment.adapter.`in`.rest.model

import io.miragon.zeebe.tm.tasklist.FormData
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class CheckPaymentSchema(
    @get:NotNull
    @get:NotBlank
    val invoiceId: String,

    @get:NotNull
    @get:NotBlank
    val orderId: String,

    @get:NotNull
    @get:NotBlank
    val amount: BigDecimal,

    val isAccepted: Boolean = false,
) : FormData
