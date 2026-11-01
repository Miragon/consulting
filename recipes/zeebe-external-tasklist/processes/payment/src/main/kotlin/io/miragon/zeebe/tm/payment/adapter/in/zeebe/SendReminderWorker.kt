package io.miragon.zeebe.tm.payment.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.miragon.zeebe.tm.payment.application.port.`in`.SendReminderUseCase
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SendReminderWorker(
    private val useCase: SendReminderUseCase
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "send-reminder-request")
    fun sendReminder()
    {
        log.info { "Task \"send-reminder\" activated." }
        useCase.handle()
    }
}