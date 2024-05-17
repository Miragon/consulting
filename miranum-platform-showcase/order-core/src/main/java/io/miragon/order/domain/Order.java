package io.miragon.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Order
{
    String customerName;

    String customerAddress;

    Map<UUID, Integer> items;

    OrderStatus status;

    public enum OrderStatus
    {
        NEW,
        IN_PROGRESS,
        DELIVERED,
        CANCELLED
    }
}
