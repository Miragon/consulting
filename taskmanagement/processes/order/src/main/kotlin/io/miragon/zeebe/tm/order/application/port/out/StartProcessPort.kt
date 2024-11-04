package io.miragon.zeebe.tm.order.application.port.out

interface StartProcessPort
{
    fun startProcess(orderId: String): Long
}