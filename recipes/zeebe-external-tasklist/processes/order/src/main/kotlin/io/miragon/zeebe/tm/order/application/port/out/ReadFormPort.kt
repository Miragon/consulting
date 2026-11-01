package io.miragon.zeebe.tm.order.application.port.out

interface ReadFormPort
{
    fun read(path: String): String
}