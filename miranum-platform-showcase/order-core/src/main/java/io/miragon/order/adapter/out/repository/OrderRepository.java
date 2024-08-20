package io.miragon.order.adapter.out.repository;

import io.miragon.order.application.port.out.OrderRepositoryPort;
import io.miragon.order.domain.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderRepository implements OrderRepositoryPort
{
    private final List<OrderEntity> orders = new ArrayList<>();

    @Override
    public UUID createOrder(Order order)
    {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity(
                id,
                "",
                order.getCustomerName(),
                order.getCustomerAddress(),
                order.getItems(),
                Order.OrderStatus.NEW.toString()
        );
        orders.add(orderEntity);
        return id;
    }

    @Override
    public Order readOrderById(UUID id) throws NoSuchElementException
    {
        var order = orders.stream().filter(o -> o.getId().equals(id)).findFirst();
        return order.map(orderEntity -> new Order(
                orderEntity.getCustomerName(),
                orderEntity.getCustomerAddress(),
                orderEntity.getItems(),
                Order.OrderStatus.valueOf(orderEntity.getStatus())
        )).orElseThrow();
    }

    @Override
    public Map<UUID, Order> readOrderByCustomerName(String customerName)
    {
        List<OrderEntity> orderEntities = orders
                .stream()
                .filter(o -> o.getCustomerName().equals(customerName))
                .toList();

        return orderEntities.stream().collect(
                Map::of,
                (map, orderEntity) -> map.put(orderEntity.getId(), new Order(
                        orderEntity.getCustomerName(),
                        orderEntity.getCustomerAddress(),
                        orderEntity.getItems(),
                        Order.OrderStatus.valueOf(orderEntity.getStatus())
                )),
                Map::putAll
        );
    }

    @Override
    public boolean updateOrder(UUID id, Order order)
    {
        orders.removeIf(o -> o.getId().equals(id));
        orders.add(new OrderEntity(
                id,
                "",
                order.getCustomerName(),
                order.getCustomerAddress(),
                order.getItems(),
                order.getStatus().toString()
        ));
        return true;
    }

    @Override
    public Order deleteOrder(UUID id) throws IllegalArgumentException
    {
        var order = orders.stream().filter(o -> o.getId().equals(id)).findFirst();
        if (order.isPresent()) {
            orders.remove(order.get());
            return new Order(
                    order.get().getCustomerName(),
                    order.get().getCustomerAddress(),
                    order.get().getItems(),
                    Order.OrderStatus.valueOf(order.get().getStatus())
            );
        }
        throw new IllegalArgumentException("Order not found");
    }
}
