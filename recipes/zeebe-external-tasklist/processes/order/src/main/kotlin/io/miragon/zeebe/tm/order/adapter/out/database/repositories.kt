package io.miragon.zeebe.tm.order.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<OrderEntity, UUID>
interface ItemRepository : JpaRepository<ItemEntity, UUID>
interface OrderItemRepository : JpaRepository<OrderItemEntity, UUID>