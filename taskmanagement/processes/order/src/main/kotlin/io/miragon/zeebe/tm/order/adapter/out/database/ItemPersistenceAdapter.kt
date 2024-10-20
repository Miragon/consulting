package io.miragon.zeebe.tm.order.adapter.out.database

import io.miragon.zeebe.tm.order.application.port.out.ItemPersistencePort
import io.miragon.zeebe.tm.order.domain.Item
import org.springframework.stereotype.Component

@Component
class ItemPersistenceAdapter(
    private val itemRepository: ItemRepository
) : ItemPersistencePort
{
    override fun findAll(): List<Item>
    {
        return itemRepository.findAll().map {
            Item(
                id = it.id.toString(),
                name = it.name,
                price = it.price,
                image = it.image,
            )
        }
    }
}
