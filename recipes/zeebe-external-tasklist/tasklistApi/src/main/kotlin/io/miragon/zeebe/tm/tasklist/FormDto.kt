package io.miragon.zeebe.tm.tasklist

/**
 * Interface for form data DTOs.
 */
interface FormData

sealed class FormDto
{
    data class JsonForm<T : FormData>(
        val schema: String,
        val uiSchema: String,
        val updatable: Boolean,
        val formData: T?
    ) : FormDto()

    data class HtmlForm<T : FormData>(
        val html: String,
        val updatable: Boolean,
        val formData: T?
    ) : FormDto()
}

