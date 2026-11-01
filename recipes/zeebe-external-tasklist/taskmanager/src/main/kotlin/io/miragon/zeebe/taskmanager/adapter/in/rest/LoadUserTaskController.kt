package io.miragon.zeebe.taskmanager.adapter.`in`.rest

import io.miragon.zeebe.taskmanager.application.port.`in`.LoadUserTaskUseCase
import io.miragon.zeebe.tm.tasklist.UserTaskDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class LoadUserTaskController(
    @Value("\${miranum.tm.exporter}") private val exporter: Boolean,
    private val loadUserTaskUseCase: LoadUserTaskUseCase,
)
{
    @GetMapping("/tasks")
    fun loadTasks(): ResponseEntity<List<UserTaskDto>>
    {
        val tasks = if (exporter)
        {
            // Should be used if user tasks are retrieved via exporter
            loadUserTaskUseCase.loadByTaskState()
        } else
        {
            // Should be used if user tasks are retrieved via job worker
            loadUserTaskUseCase.loadByExpirationDate()
        }

        return ResponseEntity.ok(tasks.map {
            UserTaskDto(
                key = it.key,
                elementId = it.elementId,
                processInstanceKey = it.processInstanceKey,
                bpmnProcessId = it.bpmnProcessId,
                processDefinitionKey = it.processDefinitionKey,
                variables = it.variables,
                taskState = it.taskState.toString(),
                assignee = it.assignee,
            )
        })
    }
}