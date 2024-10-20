package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.CompleteCheckPaymentUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.CompleteCheckPaymentUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import io.miragon.zeebe.tm.payment.application.port.out.TaskManagerPort
import io.miragon.zeebe.tm.payment.domain.Invoice
import org.springframework.stereotype.Service

@Service
class CompleteCheckPaymentService(
    private val invoicePersistencePort: InvoicePersistencePort,
    private val taskManagerPort: TaskManagerPort,
) : CompleteCheckPaymentUseCase
{
    override fun complete(command: Command): Long
    {
        val (taskId, invoiceId, isAccepted) = command

        // 1. Update invoice state
        val invoice = invoicePersistencePort.findById(invoiceId)
        invoice.state = if (isAccepted) Invoice.State.ACCEPTED else Invoice.State.REJECTED
        invoicePersistencePort.update(invoice)

        // 2. Complete task
        taskManagerPort.completeCheckPaymentTask(taskId, isAccepted)

        return taskId
    }
}