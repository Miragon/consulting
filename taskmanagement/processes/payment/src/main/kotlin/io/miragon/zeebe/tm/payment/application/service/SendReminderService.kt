package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.SendReminderUseCase
import io.miragon.zeebe.tm.payment.application.port.out.SendReminderPort
import org.springframework.stereotype.Service

@Service
class SendReminderService(
    private val sendReminderPort: SendReminderPort,
) : SendReminderUseCase
{
    override fun handle()
    {
        sendReminderPort.publish()
    }
}