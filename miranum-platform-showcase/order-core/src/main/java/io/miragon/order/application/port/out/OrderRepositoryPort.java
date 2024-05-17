package io.miragon.order.application.port.out;

import io.miragon.order.domain.Order;

import java.util.Map;
import java.util.UUID;

public interface OrderRepositoryPort
{
    UUID createOrder(Order order);

    Order readOrderById(UUID id);

    Map<UUID, Order> readOrderByCustomerName(String customerName);

    boolean updateOrder(UUID id, Order order);

    Order deleteOrder(UUID id);
}
