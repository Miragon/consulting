package io.miragon.order.adapter.out.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ItemEntity
{
    UUID id;

    String name;

    String description;

    double price;
}
