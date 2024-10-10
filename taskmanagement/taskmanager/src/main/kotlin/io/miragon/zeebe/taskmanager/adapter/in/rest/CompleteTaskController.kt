package io.miragon.zeebe.taskmanager.adapter.`in`.rest

import io.miragon.zeebe.taskmanager.adapter.`in`.rest.model.CompleteTaskCommand
import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase.Command
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class CompleteTaskController(
    private val useCase: CompleteTaskUseCase
)
{
    @PostMapping("/task/complete")
    fun completeTask(@RequestBody command: CompleteTaskCommand): ResponseEntity<Boolean>
    {
        val (taskId, variables) = command
        val command = Command(taskId, variables)
        return ResponseEntity.ok(useCase.complete(command))
    }
}