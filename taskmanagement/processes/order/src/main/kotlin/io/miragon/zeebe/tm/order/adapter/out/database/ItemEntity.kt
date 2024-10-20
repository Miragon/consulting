package io.miragon.zeebe.tm.order.adapter.out.database

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "items")
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: BigDecimal,

    @Column(nullable = false)
    val image: String,
)