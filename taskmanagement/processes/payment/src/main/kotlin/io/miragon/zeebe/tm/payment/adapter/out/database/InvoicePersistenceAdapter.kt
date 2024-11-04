package io.miragon.zeebe.tm.payment.adapter.out.database

import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import io.miragon.zeebe.tm.payment.domain.Invoice
import org.springframework.stereotype.Component
import java.util.*

@Component
class InvoicePersistenceAdapter(
    private val invoiceRepository: InvoiceRepository
) : InvoicePersistencePort
{
    override fun findById(id: String): Invoice
    {
        val uuid = UUID.fromString(id)
        val invoice = invoiceRepository.findById(uuid).orElseThrow { throw RuntimeException("Invoice not found") }
        return toInvoice(invoice)
    }

    override fun update(invoice: Invoice): String
    {
        val invoiceEntity = toInvoiceEntity(invoice)
        val res = invoiceRepository.save(invoiceEntity)
        return res.id.toString()
    }

    override fun save(order: Invoice): String
    {
        val invoiceEntity = toInvoiceEntity(order)
        val invoiceSaved = invoiceRepository.save(invoiceEntity)
        return invoiceSaved.id.toString()
    }

    private fun toInvoice(invoiceEntity: InvoiceEntity): Invoice
    {
        return Invoice(
            id = invoiceEntity.id.toString(),
            orderId = invoiceEntity.orderId,
            amount = invoiceEntity.amount,
            state = Invoice.State.valueOf(invoiceEntity.state),
        )
    }

    private fun toInvoiceEntity(invoice: Invoice): InvoiceEntity
    {
        return InvoiceEntity(
            id = invoice.id?.let { UUID.fromString(it) },
            orderId = invoice.orderId,
            amount = invoice.amount,
            state = invoice.state.name,
        )
    }
}
