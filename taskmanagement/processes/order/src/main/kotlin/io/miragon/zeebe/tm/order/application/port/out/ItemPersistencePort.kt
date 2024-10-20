package io.miragon.zeebe.tm.order.application.port.out

import io.miragon.zeebe.tm.order.domain.Item

interface ItemPersistencePort
{
    fun findAll(): List<Item>
}