package io.miragon.zeebe.tm.order.application.port.out

interface CancelOrderPort
{
    fun correlateMessage(orderId: String)
}
