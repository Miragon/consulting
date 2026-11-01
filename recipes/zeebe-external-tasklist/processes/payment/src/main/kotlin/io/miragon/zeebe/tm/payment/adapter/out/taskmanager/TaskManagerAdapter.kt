package io.miragon.zeebe.tm.payment.adapter.out.taskmanager

import io.miragon.libs.taskmanager.client.apis.CompleteTaskControllerApi
import io.miragon.libs.taskmanager.client.models.CompleteTaskCommand
import io.miragon.zeebe.tm.payment.application.port.out.TaskManagerPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException

@Component
class TaskManagerAdapter(
    restClient: RestClient
) : TaskManagerPort
{
    private val log = KotlinLogging.logger {}

    private val apiClient = CompleteTaskControllerApi(restClient)

    override fun completeCheckPaymentTask(taskId: Long, approved: Boolean): Boolean
    {
        val variables = mapOf("isAccepted" to approved)
        try
        {
            val command = CompleteTaskCommand(taskId, variables)
            return apiClient.completeTask(command)
        } catch (e: RestClientResponseException)
        {
            log.error { "Failed to complete task with id $taskId" }
            throw e
        }
    }
}
