package io.miragon.zeebe.tm.order.adapter.out.fs

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FileSystemAdapterTest
{
    private val formPersistenceAdapter = FileSystemAdapter()

    private val directory = "/forms"

    @Test
    fun readProcessStartForm()
    {
        // Arrange
        val fileName = "index.html"

        // Read content
        val read = formPersistenceAdapter.readProcessStartForm("$directory/$fileName")

        assert(read.html.isNotEmpty())
    }

    @Test
    fun readCheckOrderForm()
    {
        // Arrange
        val fileName = "index.html"

        // Read content
        val read = formPersistenceAdapter.readCheckOrderForm("$directory/$fileName")

        assert(read.html.isNotEmpty())
    }

    @Test
    fun readPrepareOrderForm()
    {
        // Arrange
        val fileName = "PrepareOrderSchema.form.json"

        // Read content
        val read = formPersistenceAdapter.readPrepareOrderForm("$directory/$fileName")

        assert(read.schema.isNotEmpty())
        assert(read.uischema.isNotEmpty())
    }

    @Test
    fun fileDoesNotExist()
    {
        // Arrange
        val fileName = "DoesNotExist.form.json"

        // Act & Assert
        assertThrows<IllegalStateException> {
            formPersistenceAdapter.readProcessStartForm("$directory/$fileName")
        }
    }
}