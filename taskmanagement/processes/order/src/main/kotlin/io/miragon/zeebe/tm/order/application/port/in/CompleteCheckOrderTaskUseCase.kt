package io.miragon.zeebe.tm.order.application.port.`in`

interface CompleteCheckOrderTaskUseCase
{
    /**
     * Completes the check order task.
     * @return The id of the completed task.
     */
    fun complete(command: Command): Long

    data class Command(
        val taskId: Long,
        val orderId: String,
        val isAccepted: Boolean,
    )
}
