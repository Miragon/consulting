package io.miragon.zeebe.tm.payment.adapter.out.database

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "invoices")
class InvoiceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val orderId: String,

    @Column(nullable = false)
    val amount: BigDecimal,

    @Column(nullable = false)
    val state: String,
)