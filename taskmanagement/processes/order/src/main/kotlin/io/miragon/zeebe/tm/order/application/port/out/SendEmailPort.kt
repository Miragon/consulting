package io.miragon.zeebe.tm.order.application.port.out

interface SendEmailPort
{
    fun publish(orderId: String, email: String)
}