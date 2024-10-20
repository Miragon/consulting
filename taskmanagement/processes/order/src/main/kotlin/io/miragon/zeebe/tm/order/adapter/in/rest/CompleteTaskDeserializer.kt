package io.miragon.zeebe.tm.order.adapter.`in`.rest

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.CheckOrder
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.PrepareDeliverySchema
import io.miragon.zeebe.tm.tasklist.CompleteTaskDto
import io.miragon.zeebe.tm.tasklist.FormData
import io.miragon.zeebe.tm.tasklist.UserTaskDto
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class CompleteTaskDeserializer : JsonDeserializer<CompleteTaskDto<FormData>>()
{
    private val mapper = jacksonObjectMapper()

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): CompleteTaskDto<FormData>
    {
        val node: JsonNode = p.codec.readTree(p)
        val id = node.get("userTask").get("elementId").asText()
        val dataNode = node.get("formData")

        val data: FormData = when (id)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                mapper.treeToValue(dataNode, CheckOrder::class.java)
            }

            UserTaskId.PREPARE_DELIVERY.id ->
            {
                mapper.treeToValue(dataNode, PrepareDeliverySchema::class.java)
            }

            else -> throw IllegalArgumentException("Unknown user task id: $id")
        }

        return CompleteTaskDto(
            userTask = mapper.treeToValue(node.get("userTask"), UserTaskDto::class.java),
            formData = data
        )
    }
}
