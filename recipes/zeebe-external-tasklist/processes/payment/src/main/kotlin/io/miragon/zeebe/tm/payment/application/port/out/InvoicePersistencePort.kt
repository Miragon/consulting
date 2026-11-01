package io.miragon.zeebe.tm.payment.application.port.out

import io.miragon.zeebe.tm.payment.domain.Invoice

interface InvoicePersistencePort
{
    fun findById(id: String): Invoice

    fun update(invoice: Invoice): String

    fun save(order: Invoice): String
}