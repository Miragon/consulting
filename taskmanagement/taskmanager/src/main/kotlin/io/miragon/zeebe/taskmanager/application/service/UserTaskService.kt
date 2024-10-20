package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.UserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.UserTaskUseCase.SaveCommand
import io.miragon.zeebe.taskmanager.application.port.`in`.UserTaskUseCase.SyncCommand
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.TaskState
import io.miragon.zeebe.taskmanager.domain.UserTask
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class UserTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort,
) : UserTaskUseCase
{
    override fun save(command: SaveCommand)
    {
        userTaskPersistencePort.save(
            UserTask(
                key = command.key,
                taskState = TaskState.CREATED,
                elementId = command.elementId,
                processInstanceKey = command.processInstanceKey,
                bpmnProcessId = command.bpmnProcessId,
                processDefinitionKey = command.processDefinitionKey,
                assignee = command.assignee,
                expiresAt = null,
            )
        )
    }

    override fun updateState(key: Long, state: String)
    {
        val matchingTask = userTaskPersistencePort.findByTaskId(key)
        val taskState = TaskState.valueOf(state)
        matchingTask.taskState = taskState
        userTaskPersistencePort.update(matchingTask)
    }

    /**
     * Syncs a task with the given command.
     * If the task does not exist, it will be created.
     * Is used if a JobWorker handles the saving and updating of tasks.
     */
    override fun sync(command: SyncCommand)
    {
        try
        {
            val matchingTask = userTaskPersistencePort.findByTaskId(command.key)
            matchingTask.extendLock(command.expiresAt)
            userTaskPersistencePort.update(matchingTask)
        } catch (e: EntityNotFoundException)
        {
            userTaskPersistencePort.save(
                UserTask(
                    key = command.key,
                    taskState = TaskState.CREATED,
                    elementId = command.elementId,
                    processInstanceKey = command.processInstanceKey,
                    bpmnProcessId = command.bpmnProcessId,
                    processDefinitionKey = command.processDefinitionKey,
                    expiresAt = command.expiresAt,
                    variables = command.variables,
                    assignee = null,
                )
            )
        }
    }
}
