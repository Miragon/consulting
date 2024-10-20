package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase.Command
import io.miragon.zeebe.taskmanager.application.port.out.CompleteTaskPort
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import jakarta.persistence.EntityNotFoundException
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class CompleteTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort,
    private val completeTaskPort: CompleteTaskPort,
) : CompleteTaskUseCase
{
    private val log = KotlinLogging.logger {}

    override fun complete(command: Command): Boolean
    {
        val (key, variables) = command
        try
        {
            val task = userTaskPersistencePort.findByTaskId(key)
            task.complete()

            val isCompleted = completeTaskPort.complete(task.key, variables)

            val taskId =
                userTaskPersistencePort.update(task) // Update database after task is marked completed in engine

            return taskId == key && isCompleted
        } catch (e: EntityNotFoundException)
        {
            log.error { "Task with id ${command.key} not found: ${e.message}\n${e.stackTrace}" }
            return false
        }
    }
}