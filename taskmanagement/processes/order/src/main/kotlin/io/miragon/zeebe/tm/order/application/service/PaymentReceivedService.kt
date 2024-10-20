package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase.Query
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.PaymentReceivedPort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class PaymentReceivedService(
    private val orderPersistencePort: OrderPersistencePort,
    private val paymentReceivedPort: PaymentReceivedPort,
) : PaymentReceivedUseCase
{
    override fun handle(query: Query)
    {
        val (orderId, invoiceId) = query

        val order = orderPersistencePort.findById(orderId)
        order.state = Order.State.PAID
        order.invoiceId = invoiceId
        orderPersistencePort.update(orderId, order)

        paymentReceivedPort.correlateMessage(orderId, invoiceId)
    }
}