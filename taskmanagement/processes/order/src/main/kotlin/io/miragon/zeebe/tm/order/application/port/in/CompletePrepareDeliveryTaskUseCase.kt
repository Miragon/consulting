package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Item


interface CompletePrepareDeliveryTaskUseCase
{
    /**
     * Completes the prepare order task.
     * @return The id of the completed task.
     */
    fun complete(command: Command): Long

    /**
     * Updates the prepare order task.
     * @return The id of the updated task.
     */
    fun update(command: Command): Long

    data class Command(
        val taskId: Long,
        val orderId: String,
        val deliveryDate: String,
        val modeOfDispatch: String,
        val items: List<Item>,
    )
}