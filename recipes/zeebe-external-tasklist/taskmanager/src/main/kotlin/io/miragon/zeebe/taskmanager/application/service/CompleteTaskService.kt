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

            // The engine is the source of truth: the task only counts as completed once the engine confirmed it.
            val isConfirmedByEngine = completeTaskPort.complete(task.key, variables)
            if (!isConfirmedByEngine)
            {
                log.warn { "Engine rejected completion of task $key, task state is left unchanged." }
                return false
            }

            task.complete()
            val taskId = userTaskPersistencePort.update(task)

            return taskId == key
        } catch (e: EntityNotFoundException)
        {
            log.error { "Task with id ${command.key} not found: ${e.message}\n${e.stackTrace}" }
            return false
        }
    }
}
