package io.miragon.zeebe.taskmanager.adapter.out.database

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.taskmanager.domain.ProcessVariable
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "process_variable")
class ProcessVariableEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val processInstanceKey: Long,

    @Column(nullable = true)
    val bpmnProcessId: String,

    @Column(nullable = true)
    val processDefinitionKey: Long,

    val name: String,

    @Convert(converter = AnyConverter::class)
    var value: Any,
)
{
    fun toDomain(): ProcessVariable
    {
        return ProcessVariable(
            processInstanceKey = processInstanceKey,
            name = name,
            value = value,
            bpmnProcessId = null,
            processDefinitionKey = null,
        )
    }
}

@Converter
class AnyConverter : AttributeConverter<Any, String>
{
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(value: Any?): String
    {
        return try
        {
            objectMapper.writeValueAsString(value)
        } catch (e: Exception)
        {
            throw IllegalArgumentException("Could not convert Any to String", e)
        }
    }

    override fun convertToEntityAttribute(json: String?): Any
    {
        return try
        {
            objectMapper.readValue(json, object : TypeReference<Any>()
            {})
        } catch (e: Exception)
        {
            throw IllegalArgumentException("Could not convert JSON to map", e)
        }
    }

}
