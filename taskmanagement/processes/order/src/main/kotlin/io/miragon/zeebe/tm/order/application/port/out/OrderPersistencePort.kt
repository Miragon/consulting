package io.miragon.zeebe.tm.order.application.port.out

import io.miragon.zeebe.tm.order.domain.Order

interface OrderPersistencePort
{
    fun findAll(): List<Order>

    fun findById(id: String): Order

    fun update(id: String, updatedOrder: Order): String

    fun save(newOrder: Order): String
}