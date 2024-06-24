package io.miragon.order.adapter.out.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemEntity
{
    String id;

    String name;

    String description;

    double price;
}
