package io.miragon.order.application.port.out;

import io.miragon.order.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepositoryPort
{
    Item readItem(UUID id);

    List<Item> readItems(UUID[] ids);
}
