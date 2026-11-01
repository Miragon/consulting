package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.SendPaymentReceivedUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.SendPaymentReceivedUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import io.miragon.zeebe.tm.payment.application.port.out.PaymentReceivedPort
import org.springframework.stereotype.Service

@Service
class SendPaymentReceivedService(
    private val paymentReceivedPort: PaymentReceivedPort,
    private val invoicePersistencePort: InvoicePersistencePort,
) : SendPaymentReceivedUseCase
{
    override fun send(command: Command)
    {
        val invoiceId = command.invoiceId
        val invoice = invoicePersistencePort.findById(invoiceId)

        paymentReceivedPort.publish(invoiceId, invoice.orderId)
    }
}