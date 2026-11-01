package io.miragon.zeebe.tm.payment.application.port.out

interface SendReminderPort
{
    fun publish()
}