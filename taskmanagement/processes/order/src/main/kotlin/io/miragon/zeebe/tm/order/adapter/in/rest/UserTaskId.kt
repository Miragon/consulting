package io.miragon.zeebe.tm.order.adapter.`in`.rest

enum class UserTaskId(val id: String)
{
    CHECK_ORDER("CheckOrder"),
    PREPARE_DELIVERY("PrepareDelivery"),
}

