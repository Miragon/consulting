package io.miragon.zeebe.tm.order.application.port.out

interface TaskManagerPort
{
    fun completeCheckOrderTask(taskId: Long, approved: Boolean): Boolean

    fun completePrepareDeliveryTask(taskId: Long): Boolean
}