package io.miragon.zeebe.tm.order.adapter.out.database

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val firstname: String,

    @Column(nullable = false)
    val lastname: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val street: String,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val zip: String,

    @Column(nullable = false)
    val state: String,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val orderItems: List<OrderItemEntity> = mutableListOf(),

    @Column(nullable = true, name = "invoice_id")
    val invoiceId: UUID?,

    @Column(nullable = true, name = "delivery_date")
    val deliveryDate: LocalDate?,

    @Column(nullable = true, name = "mode_of_dispatch")
    val modeOfDispatch: String?,
)
{
    fun copy(
        firstname: String = this.firstname,
        lastname: String = this.lastname,
        email: String = this.email,
        street: String = this.street,
        city: String = this.city,
        zip: String = this.zip,
        orderItems: List<OrderItemEntity> = this.orderItems,
        state: String = this.state,
        invoiceId: UUID? = this.invoiceId,
        deliveryDate: LocalDate? = this.deliveryDate,
        modeOfDispatch: String? = this.modeOfDispatch,
    ): OrderEntity
    {
        return OrderEntity(
            id = this.id,
            firstname = firstname,
            lastname = lastname,
            email = email,
            street = street,
            city = city,
            zip = zip,
            orderItems = orderItems,
            state = state,
            invoiceId = invoiceId,
            deliveryDate = deliveryDate,
            modeOfDispatch = modeOfDispatch,
        )
    }
}