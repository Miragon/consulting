package io.miragon.order.adapter.out.repository;

import io.miragon.order.application.port.out.ItemRepositoryPort;
import io.miragon.order.domain.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemRepository implements ItemRepositoryPort
{
    private final List<ItemEntity> items = List.of(
            new ItemEntity("123", "Item 1", "Item 1", 10.0),
            new ItemEntity("456", "Item 2", "Item 2", 20.0),
            new ItemEntity("789", "Item 3", "Item 3", 30.0)
    );

    @Override
    public List<Item> readAllItems()
    {
        return items.stream()
                .map(itemEntity -> new Item(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription(), itemEntity.getPrice()))
                .toList();
    }

    @Override
    public List<Item> readItemsById(String... ids)
    {
        return items.stream()
                .filter(itemEntity -> List.of(ids).contains(itemEntity.getId()))
                .map(itemEntity -> new Item(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription(), itemEntity.getPrice()))
                .toList();
    }
}
