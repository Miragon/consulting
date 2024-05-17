package io.miragon.order.application.service;

import io.miragon.order.application.port.in.PlaceOrderUseCase;
import io.miragon.order.application.port.out.ItemRepositoryPort;
import io.miragon.order.application.port.out.OrderRepositoryPort;
import io.miragon.order.application.port.out.StartProcessPort;
import io.miragon.order.domain.Item;
import io.miragon.order.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase
{
    private final StartProcessPort startProcessPort;

    private final ItemRepositoryPort itemRepositoryPort;

    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public UUID placeOrder(PlaceOrderCommand placeOrderCommand) throws IllegalArgumentException
    {
        Order order = new Order(
                placeOrderCommand.getCustomerName(),
                placeOrderCommand.getCustomerAddress(),
                placeOrderCommand.getItems(),
                Order.OrderStatus.NEW
        );

        // Check if the ordered items exist
        UUID[] itemIds = order.getItems().keySet().toArray(new UUID[0]);
        List<Item> items = itemRepositoryPort.readItems(itemIds);
        if (items.size() != itemIds.length) {
            throw new IllegalArgumentException("Invalid item ids");
        }

        // Save order to a database
        UUID orderId = orderRepositoryPort.createOrder(order);

        // Start process instance with order value
        double orderValue = items.stream()
                .mapToDouble(item -> item.getPrice() * order.getItems().get(item.getId()))
                .sum();
        // TODO: How do I get the process instance id back?
        startProcessPort.startProcess(orderId.toString(), orderValue);

        return orderId;
    }
}
