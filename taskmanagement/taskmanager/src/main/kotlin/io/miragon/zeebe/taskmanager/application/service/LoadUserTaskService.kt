package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.LoadUserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.out.ProcessVariablePersistencePort
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.TaskState
import io.miragon.zeebe.taskmanager.domain.UserTask
import org.springframework.stereotype.Service

@Service
class LoadUserTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort,
    private val processVariablePersistencePort: ProcessVariablePersistencePort,
) : LoadUserTaskUseCase
{
    override fun loadByExpirationDate(): List<UserTask>
    {
        return userTaskPersistencePort.findAllActiveTasks()
    }

    override fun loadByTaskState(): List<UserTask>
    {
        val userTasks = userTaskPersistencePort.findByTaskState(TaskState.CREATED)

        for (userTask in userTasks)
        {
            val processVariables = processVariablePersistencePort.findByProcessInstanceKey(userTask.processInstanceKey)
            val variables = processVariables.map { it.name to it.value }.toMap()

            userTask.variables = variables
        }

        return userTasks
    }
}