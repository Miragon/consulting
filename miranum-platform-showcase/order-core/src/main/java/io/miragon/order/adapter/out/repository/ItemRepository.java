package io.miragon.order.adapter.out.repository;

import io.miragon.order.application.port.out.ItemRepositoryPort;
import io.miragon.order.domain.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class ItemRepository implements ItemRepositoryPort
{
    private final List<ItemEntity> items = List.of(
            new ItemEntity(UUID.randomUUID(), "Item 1", "Item 1", 10.0),
            new ItemEntity(UUID.randomUUID(), "Item 2", "Item 2", 20.0),
            new ItemEntity(UUID.randomUUID(), "Item 3", "Item 3", 30.0)
    );

    @Override
    public Item readItem(UUID id) throws NoSuchElementException
    {
        return items.stream()
                .filter(itemEntity -> itemEntity.getId().equals(id))
                .map(itemEntity -> new Item(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription(), itemEntity.getPrice()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<Item> readItems(UUID[] ids)
    {
        return items.stream()
                .filter(itemEntity -> List.of(ids).contains(itemEntity.getId()))
                .map(itemEntity -> new Item(itemEntity.getId(), itemEntity.getName(), itemEntity.getDescription(), itemEntity.getPrice()))
                .toList();
    }
}
