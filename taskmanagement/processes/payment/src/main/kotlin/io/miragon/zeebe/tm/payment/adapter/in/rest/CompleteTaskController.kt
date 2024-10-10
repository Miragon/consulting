package io.miragon.zeebe.tm.payment.adapter.`in`.rest

import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CheckPaymentSchema
import io.miragon.zeebe.tm.payment.application.port.`in`.CompleteCheckPaymentUseCase
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
    private val useCase: CompleteCheckPaymentUseCase
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
            is CheckPaymentSchema ->
            {
                val command = data.toCommand(userTask.key, userTask.variables["invoiceId"].toString())
                val taskId = useCase.complete(command)
                val message = MessageDto("""Task $taskId completed""")
                return ResponseEntity.ok(message)
            }

            else -> return ResponseEntity.badRequest().build()
        }
    }
}
