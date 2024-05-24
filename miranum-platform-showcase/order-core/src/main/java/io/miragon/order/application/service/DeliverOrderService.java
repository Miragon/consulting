package io.miragon.order.application.service;

import io.miragon.order.application.port.in.DeliverOrderUseCase;
import io.miragon.order.application.port.out.OrderRepositoryPort;
import io.miragon.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class DeliverOrderService implements DeliverOrderUseCase
{
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public void deliverItems(DeliverItemsCommand command)
    {
        var orderId = command.getOrderId();
        Order order = orderRepositoryPort.readOrderById(orderId);
        order.setStatus(Order.OrderStatus.DELIVERED);
        orderRepositoryPort.updateOrder(orderId, order);

        log.info("Order {} with items {} has been delivered", orderId, order.getItems());
    }
}
