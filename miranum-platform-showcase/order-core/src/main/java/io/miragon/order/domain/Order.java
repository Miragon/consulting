package io.miragon.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Order
{
    String customerName;

    String customerAddress;

    Map<String, Integer> items;

    OrderStatus status;

    public enum OrderStatus
    {
        NEW,
        IN_PROGRESS,
        DELIVERED,
        CANCELLED
    }
}
