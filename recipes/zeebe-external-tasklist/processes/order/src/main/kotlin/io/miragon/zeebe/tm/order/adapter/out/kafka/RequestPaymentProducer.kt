package io.miragon.zeebe.tm.order.adapter.out.kafka

import io.miragon.zeebe.tm.libs.shared.kafka.PaymentRequest
import io.miragon.zeebe.tm.order.application.port.out.RequestPaymentPort
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class RequestPaymentProducer(
    private val kafkaTemplate: KafkaTemplate<String, PaymentRequest>
) : RequestPaymentPort
{
    private val log = KotlinLogging.logger {}

    override fun publish(orderId: String, amount: BigDecimal)
    {
        val request = PaymentRequest(orderId, amount)

        // This will start the payment process
        kafkaTemplate
            .send("payment-request", request)
            .join()

        log.info { "Request \"payment-request\" sent." }
    }
}