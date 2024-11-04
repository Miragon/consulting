package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.tasklist.FormData
import jakarta.annotation.Nonnull
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class PrepareDeliverySchema(
    val itemCheckList: List<CheckItemDto> = ArrayList(),

    @get:NotNull
    @get:NotBlank(message = "Please provide the delivery date of this order.")
    val deliveryDate: String,

    @get:Nonnull
    @get:NotBlank(message = "Please provide the mode of dispatch.")
    val modeOfDispatch: ModeOfDispatch,

    ) : FormData

data class CheckItemDto(
    val id: String,
    val name: String,
    val quantity: Int,
    val ready: Boolean,
)

enum class ModeOfDispatch
{
    STANDARD,
    EXPRESS,
}