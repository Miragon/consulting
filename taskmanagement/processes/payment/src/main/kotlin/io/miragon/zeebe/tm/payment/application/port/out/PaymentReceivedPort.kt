package io.miragon.zeebe.tm.payment.application.port.out

interface PaymentReceivedPort
{
    fun publish(invoiceId: String, orderId: String)
}