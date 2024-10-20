package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.CheckOrder
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.PrepareDeliverySchema
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareDeliveryTaskUseCase
import io.miragon.zeebe.tm.tasklist.CompleteTaskDto
import io.miragon.zeebe.tm.tasklist.FormData
import io.miragon.zeebe.tm.tasklist.MessageDto
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class CompleteTaskController(
    private val checkOrderUseCase: CompleteCheckOrderTaskUseCase,
    private val prepareDeliveryUseCase: CompletePrepareDeliveryTaskUseCase,
)
{
    private val log = KotlinLogging.logger {}

    @PostMapping("/complete")
    fun completeTask(@RequestBody completeTaskDto: CompleteTaskDto<FormData>): ResponseEntity<MessageDto>
    {
        val userTask = completeTaskDto.userTask

        log.info { "Completing user task: ${userTask.elementId}" }

        when (val data = completeTaskDto.formData)
        {
            is CheckOrder ->
            {
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val taskId = checkOrderUseCase.complete(command)
                val message = MessageDto("""Task $taskId completed""")
                return ResponseEntity.ok(message)
            }

            is PrepareDeliverySchema ->
            {
                try
                {
                    val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                    val taskId = prepareDeliveryUseCase.complete(command)
                    val message = MessageDto("""Task $taskId completed""")
                    return ResponseEntity.ok(message)
                } catch (e: IllegalStateException)
                {
                    val message = MessageDto(e.message ?: "Task could not be completed")
                    return ResponseEntity.ok(message)
                }
            }

            else -> return ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/update")
    fun updateTask(@RequestBody completeTaskDto: CompleteTaskDto<FormData>): ResponseEntity<MessageDto>
    {
        val userTask = completeTaskDto.userTask
        val formData = completeTaskDto.formData

        log.info { "Updating user task: ${userTask.elementId}" }

        return when (userTask.elementId)
        {
            UserTaskId.PREPARE_DELIVERY.id ->
            {
                val data = formData as PrepareDeliverySchema
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val taskId = prepareDeliveryUseCase.update(command)
                val message = MessageDto("""Task $taskId updated""")
                ResponseEntity.ok(message)
            }

            else -> ResponseEntity.badRequest().build()
        }
    }
}
