package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import io.miragon.zeebe.tm.payment.application.port.out.StartProcessPort
import io.miragon.zeebe.tm.payment.domain.Invoice
import org.springframework.stereotype.Service

@Service
class CreateInvoiceService(
    private val invoicePersistencePort: InvoicePersistencePort,
    private val startProcessPort: StartProcessPort,
) : CreateInvoiceUseCase
{
    override fun create(command: Command)
    {
        val (orderId, amount) = command
        val invoice = Invoice(
            id = null,
            orderId = orderId,
            amount = amount,
            state = Invoice.State.CREATED,
        )

        val invoiceId = invoicePersistencePort.save(invoice)
        startProcessPort.startProcess(invoiceId)
    }
}
