package io.miragon.order.application.port.out;

import io.miragon.order.domain.Item;

import java.util.List;

public interface ItemRepositoryPort
{
    List<Item> readAllItems();

    List<Item> readItemsById(String... ids);
}
