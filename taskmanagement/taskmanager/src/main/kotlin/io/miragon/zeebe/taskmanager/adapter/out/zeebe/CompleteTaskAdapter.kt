package io.miragon.zeebe.taskmanager.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.taskmanager.application.port.out.CompleteTaskPort
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(
    @Value("\${miranum.tm.exporter}") private val exporter: Boolean,
    private val zeebeClient: ZeebeClient,
) : CompleteTaskPort
{
    private val log = KotlinLogging.logger {}

    override fun complete(
        taskId: Long,
        variables: Map<String, Any>?
    ): Boolean
    {
        try
        {
            if (exporter)
            {
                completeUserTask(taskId, variables ?: emptyMap())
            } else
            {
                completeJob(taskId, variables ?: emptyMap())
            }

            return true
        } catch (e: Exception)
        {
            log.error { "Failed to complete task with id $taskId: ${e.message}\n${e.stackTrace}" }
            return false
        }
    }

    private fun completeJob(taskId: Long, variables: Map<String, Any>)
    {
        zeebeClient
            .newCompleteCommand(taskId)
            .variables(variables)
            .send()
            .join()
    }

    private fun completeUserTask(taskId: Long, variables: Map<String, Any>)
    {
        zeebeClient
            .newUserTaskCompleteCommand(taskId)
            .variables(variables)
            .send()
            .join()
    }
}
