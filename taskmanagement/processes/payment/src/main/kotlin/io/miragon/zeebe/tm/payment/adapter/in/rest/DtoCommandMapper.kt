package io.miragon.zeebe.tm.payment.adapter.`in`.rest

import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CheckPaymentSchema
import io.miragon.zeebe.tm.payment.application.port.`in`.CompleteCheckPaymentUseCase

fun CheckPaymentSchema.toCommand(
    taskId: Long,
    invoiceId: String
): CompleteCheckPaymentUseCase.Command
{
    return CompleteCheckPaymentUseCase.Command(
        taskId = taskId,
        invoiceId = invoiceId,
        isAccepted = this.isAccepted,
    )
}
