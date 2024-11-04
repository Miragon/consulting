package io.miragon.zeebe.tm.order.adapter.out.database

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "order_items")
class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderEntity,


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    val item: ItemEntity,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val ready: Boolean,
)
{
    fun copy(
        quantity: Int = this.quantity,
        ready: Boolean = this.ready,
    ): OrderItemEntity
    {
        return OrderItemEntity(
            id = this.id,
            order = this.order,
            item = this.item,
            quantity = quantity,
            ready = ready,
        )
    }
}