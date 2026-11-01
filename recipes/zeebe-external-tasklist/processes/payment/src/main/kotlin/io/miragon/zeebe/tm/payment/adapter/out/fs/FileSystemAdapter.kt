package io.miragon.zeebe.tm.payment.adapter.out.fs

import io.miragon.zeebe.tm.payment.application.port.out.ReadFormPort
import org.springframework.stereotype.Component

@Component
class FileSystemAdapter : ReadFormPort
{
    override fun read(path: String): String = readText(path) ?: throw IllegalStateException("Form not found: $path")

    private fun readText(path: String) = this::class.java.getResource(path)?.readText()
}