package io.miragon.zeebe.tm.payment.application.port.out

interface TaskManagerPort
{
    fun completeCheckPaymentTask(taskId: Long, approved: Boolean): Boolean
}