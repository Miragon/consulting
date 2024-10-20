package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase.Response
import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import io.miragon.zeebe.tm.payment.application.port.out.ReadFormPort
import org.springframework.stereotype.Service

@Service
class LoadCheckPaymentTaskService(
    private val readFormPort: ReadFormPort,
    private val invoicePersistencePort: InvoicePersistencePort,
) : LoadCheckPaymentTaskUseCase
{
    override fun load(command: Command): Response
    {
        val (invoiceId, filePath) = command

        val jsonString = readFormPort.read(filePath)
        val invoice = invoicePersistencePort.findById(invoiceId)

        return Response(
            jsonString = jsonString,
            invoice = invoice,
        )
    }
}