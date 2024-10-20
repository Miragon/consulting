package io.miragon.zeebe.tm.payment.adapter.out.kafka

import io.miragon.zeebe.tm.payment.application.port.out.SendReminderPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SendReminderProducer : SendReminderPort
{
    private val log = KotlinLogging.logger {}

    override fun publish()
    {
        log.info { "Send reminder" }
    }
}
