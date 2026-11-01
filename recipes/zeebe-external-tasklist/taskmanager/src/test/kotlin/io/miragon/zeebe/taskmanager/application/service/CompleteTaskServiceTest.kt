package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase.Command
import io.miragon.zeebe.taskmanager.application.port.out.CompleteTaskPort
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.TaskState
import io.miragon.zeebe.taskmanager.domain.UserTask
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CompleteTaskServiceTest
{
    private val openTask = UserTask(
        key = 1L,
        elementId = "CheckOrder",
        processInstanceKey = 2L,
        bpmnProcessId = "orderProcess",
        processDefinitionKey = 3L,
        taskState = TaskState.CREATED,
        expiresAt = null,
        assignee = null,
    )

    @Test
    fun `stores COMPLETED once the engine confirmed the completion`()
    {
        val persistence = InMemoryUserTaskPersistence(openTask.copy())
        val service = CompleteTaskService(persistence, FixedCompleteTaskPort(isConfirmed = true))

        assertTrue(service.complete(Command(openTask.key, emptyMap())))
        assertEquals(TaskState.COMPLETED, persistence.stored.taskState)
    }

    @Test
    fun `leaves the task state unchanged if the engine rejects the completion`()
    {
        val persistence = InMemoryUserTaskPersistence(openTask.copy())
        val service = CompleteTaskService(persistence, FixedCompleteTaskPort(isConfirmed = false))

        assertFalse(service.complete(Command(openTask.key, emptyMap())))
        assertEquals(TaskState.CREATED, persistence.stored.taskState)
        assertEquals(0, persistence.updates)
    }

    private class FixedCompleteTaskPort(private val isConfirmed: Boolean) : CompleteTaskPort
    {
        override fun complete(taskId: Long, variables: Map<String, Any>?) = isConfirmed
    }

    private class InMemoryUserTaskPersistence(var stored: UserTask) : UserTaskPersistencePort
    {
        var updates = 0

        override fun findByTaskState(taskState: TaskState) = listOf(stored).filter { it.taskState == taskState }

        override fun findAllActiveTasks() = listOf(stored)

        override fun findByTaskId(taskId: Long) = stored.copy()

        override fun update(updatedTask: UserTask): Long
        {
            updates++
            stored = updatedTask
            return updatedTask.key
        }

        override fun save(task: UserTask): Long
        {
            stored = task
            return task.key
        }
    }
}
