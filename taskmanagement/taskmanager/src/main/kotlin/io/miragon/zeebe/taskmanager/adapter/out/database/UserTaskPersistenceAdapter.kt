package io.miragon.zeebe.taskmanager.adapter.out.database

import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.TaskState
import io.miragon.zeebe.taskmanager.domain.UserTask
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class UserTaskPersistenceAdapter(
    private val userTaskRepository: UserTaskRepository
) : UserTaskPersistencePort
{
    override fun findByTaskState(taskState: TaskState): List<UserTask>
    {
        return userTaskRepository.findByTaskState(taskState.toString()).map { it.toDomain() }
    }

    override fun findAllActiveTasks(): List<UserTask>
    {
        return userTaskRepository.findByExpiresAtAfter(Instant.now()).map { it.toDomain() }
    }

    override fun findByTaskId(taskId: Long): UserTask
    {
        return userTaskRepository.findById(taskId).map { it.toDomain() }
            .orElseThrow { EntityNotFoundException("Task with id $taskId not found") }
    }

    override fun update(updatedTask: UserTask): Long
    {
        val existingTask = userTaskRepository.findById(updatedTask.key)
            .orElseThrow { EntityNotFoundException("Task with id ${updatedTask.key} not found") }

        existingTask.expiresAt = updatedTask.expiresAt
        existingTask.taskState = updatedTask.taskState.toString()
        existingTask.variables = updatedTask.variables
        existingTask.assignee = updatedTask.assignee

        return userTaskRepository.save(existingTask).id
    }

    override fun save(task: UserTask): Long
    {
        val userTaskEntity = UserTaskEntity(
            id = task.key,
            elementId = task.elementId,
            processInstanceKey = task.processInstanceKey,
            bpmnProcessId = task.bpmnProcessId,
            processDefinitionKey = task.processDefinitionKey,
            variables = task.variables,
            expiresAt = task.expiresAt,
            assignee = task.assignee,
            taskState = task.taskState.toString()
        )

        return userTaskRepository.save(userTaskEntity).id
    }
}
