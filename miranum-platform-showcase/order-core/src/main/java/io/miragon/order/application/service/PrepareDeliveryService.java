package io.miragon.order.application.service;

import io.miragon.order.application.port.in.PrepareDeliveryUseCase;
import io.miragon.order.application.port.out.OrderRepositoryPort;
import io.miragon.order.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PrepareDeliveryService implements PrepareDeliveryUseCase
{
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public void prepareDelivery(PrepareDeliveryCommand command)
    {
        var orderId = command.getOrderId();
        Order order = orderRepositoryPort.readOrderById(orderId);
        order.setStatus(Order.OrderStatus.IN_PROGRESS);
        orderRepositoryPort.updateOrder(orderId, order);
    }
}
