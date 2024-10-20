package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.SendCancellationUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.SendCancellationUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.out.CancelOrderPort
import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import org.springframework.stereotype.Service

@Service
class SendCancellationService(
    private val cancelOrderPort: CancelOrderPort,
    private val invoicePersistencePort: InvoicePersistencePort
) : SendCancellationUseCase
{
    override fun send(command: Command)
    {
        val invoiceId = command.invoiceId
        val invoice = invoicePersistencePort.findById(invoiceId)

        cancelOrderPort.publish(invoice.orderId)
    }
}