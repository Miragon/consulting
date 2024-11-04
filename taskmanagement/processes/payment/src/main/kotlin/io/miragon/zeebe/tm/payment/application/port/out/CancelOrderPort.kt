package io.miragon.zeebe.tm.payment.application.port.out

interface CancelOrderPort
{
    fun publish(orderId: String)
}