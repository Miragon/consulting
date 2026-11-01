package io.miragon.zeebe.tm.order.adapter.out.database

import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class OrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
) : OrderPersistencePort
{
    override fun findAll(): List<Order>
    {
        return orderRepository.findAll().map { toOrder(it) }
    }

    override fun findById(id: String): Order
    {
        val uuid = UUID.fromString(id)
        val orderEntity =
            orderRepository.findById(uuid).orElseThrow { throw EntityNotFoundException("Order not found") }
        return toOrder(orderEntity)
    }

    @Transactional
    override fun update(id: String, updatedOrder: Order): String
    {
        val existingOrder = orderRepository.findById(UUID.fromString(id))
            .orElseThrow { throw EntityNotFoundException("Order not found") }

        val newOrder = existingOrder.copy(
            firstname = updatedOrder.firstname,
            lastname = updatedOrder.lastname,
            email = updatedOrder.email,
            street = updatedOrder.street,
            city = updatedOrder.city,
            zip = updatedOrder.zip,
            orderItems = updateOrderItems(existingOrder, updatedOrder.items),
            state = updatedOrder.state?.name ?: throw IllegalArgumentException("State must be set"),
            invoiceId = existingOrder.invoiceId ?: updatedOrder.invoiceId?.let { UUID.fromString(it) },
            deliveryDate = updatedOrder.deliveryDate,
            modeOfDispatch = updatedOrder.modeOfDispatch,
        )

        val res = orderRepository.save(newOrder)
        return res.id.toString()
    }

    @Transactional
    override fun save(newOrder: Order): String
    {
        // 1. Save order without order items
        val orderEntity = OrderEntity(
            firstname = newOrder.firstname,
            lastname = newOrder.lastname,
            email = newOrder.email,
            street = newOrder.street,
            city = newOrder.city,
            zip = newOrder.zip,
            state = newOrder.state?.name ?: throw IllegalArgumentException("State must be set"),
            invoiceId = null,
            deliveryDate = null,
            modeOfDispatch = null,
        )

        val savedOrderEntity = orderRepository.save(orderEntity)

        // 2. Add order items to saved order
        val orderItemsEntity = newOrder.items.map {
            val itemEntity = itemRepository
                .findById(UUID.fromString(it.id))
                .orElseThrow {
                    throw EntityNotFoundException("Item with id ${it.id} not found")
                }

            OrderItemEntity(
                order = orderEntity,
                item = itemEntity,
                quantity = it.quantity ?: throw IllegalArgumentException("Quantity must be set"),
                ready = it.ready ?: false,
            )
        }

        val completeOrder = savedOrderEntity.copy(orderItems = orderItemsEntity)

        // 3. Save complete order
        val savedOrder = orderRepository.save(completeOrder)

        return savedOrder.id.toString()
    }

    private fun updateOrderItems(existingOrderEntity: OrderEntity, updatedItems: List<Item>): List<OrderItemEntity>
    {
        val existingOrderItems = existingOrderEntity.orderItems.toMutableList()

        // Remove items that are not in the new order
        existingOrderItems.removeIf { existingOrderItem ->
            updatedItems.none { it.id == existingOrderItem.item.id.toString() }
        }

        // Update existing items or add new ones
        updatedItems.forEach { updatedItem ->
            val existingOrderItem = existingOrderItems.find { it.item.id.toString() == updatedItem.id }
            if (existingOrderItem != null)
            {
                existingOrderItem.copy(
                    quantity = updatedItem.quantity ?: throw IllegalArgumentException("Quantity must be set"),
                    ready = updatedItem.ready ?: false,
                )
            } else
            {
                val itemEntity = itemRepository
                    .findById(UUID.fromString(updatedItem.id))
                    .orElseThrow {
                        throw EntityNotFoundException("Item with id ${updatedItem.id} not found")
                    }

                existingOrderItems.add(
                    OrderItemEntity(
                        order = existingOrderEntity,
                        item = itemEntity,
                        quantity = updatedItem.quantity ?: throw IllegalArgumentException("Quantity must be set"),
                        ready = updatedItem.ready ?: false,
                    )
                )
            }
        }

        return existingOrderItems
    }

    private fun toOrder(orderEntity: OrderEntity): Order
    {
        return Order(
            firstname = orderEntity.firstname,
            lastname = orderEntity.lastname,
            email = orderEntity.email,
            street = orderEntity.street,
            city = orderEntity.city,
            zip = orderEntity.zip,
            items = orderEntity.orderItems.map {
                Item(
                    id = it.item.id.toString(),
                    name = it.item.name,
                    price = it.item.price,
                    image = it.item.image,
                    quantity = it.quantity
                )
            },
            state = Order.State.valueOf(orderEntity.state),
            invoiceId = orderEntity.invoiceId?.toString(),
            deliveryDate = orderEntity.deliveryDate,
            modeOfDispatch = orderEntity.modeOfDispatch,
        )
    }
}
