package io.miragon.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item
{
    String id;

    String name;

    String description;

    double price;
}
