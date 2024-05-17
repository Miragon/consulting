package io.miragon.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Item
{
    UUID id;

    String name;

    String description;

    double price;
}
