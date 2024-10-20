package io.miragon.zeebe.tm.order.domain

import java.math.BigDecimal

data class Item(
    val id: String,
    val name: String? = null,
    val price: BigDecimal? = null,
    val image: String? = null,
    val quantity: Int? = null,
    val ready: Boolean? = null,
)