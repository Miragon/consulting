package io.miragon.order.adapter.out.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderEntity
{
    UUID id;

    String processInstanceId;

    String customerName;

    String customerAddress;

    Map<String, Integer> items;

    String status;
}
