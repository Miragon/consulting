package io.miragon.order.application.service;

import io.miragon.order.application.port.in.GetOrderUseCase;
import io.miragon.order.application.port.out.OrderRepositoryPort;
import io.miragon.order.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetOrderService implements GetOrderUseCase
{
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public Order getOrder(GetOrderQuery query)
    {
        return orderRepositoryPort.readOrderById(query.getOrderId());
    }
}
