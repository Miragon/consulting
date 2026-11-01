package io.miragon.zeebe.tm.payment.application.port.out

interface ReadFormPort
{
    fun read(path: String): String
}