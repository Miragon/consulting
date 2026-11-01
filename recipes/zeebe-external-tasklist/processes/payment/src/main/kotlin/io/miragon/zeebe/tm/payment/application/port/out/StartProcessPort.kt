package io.miragon.zeebe.tm.payment.application.port.out

interface StartProcessPort
{
    fun startProcess(invoiceId: String): Long
}