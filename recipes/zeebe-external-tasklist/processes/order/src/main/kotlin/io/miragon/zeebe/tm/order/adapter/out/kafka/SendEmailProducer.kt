package io.miragon.zeebe.tm.order.adapter.out.kafka

import io.miragon.zeebe.tm.order.application.port.out.SendEmailPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SendEmailProducer : SendEmailPort
{
    private val log = KotlinLogging.logger {}

    override fun publish(orderId: String, email: String)
    {
        log.info { "To: $email\nSubject: #$orderId declined" }
    }
}