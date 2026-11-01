package io.miragon.zeebe.tm.order.application.port.out

import java.math.BigDecimal

interface RequestPaymentPort
{
    /**
     * Requests payment for the order.
     * @return The id of the created payment request.
     */
    fun publish(orderId: String, amount: BigDecimal)
}