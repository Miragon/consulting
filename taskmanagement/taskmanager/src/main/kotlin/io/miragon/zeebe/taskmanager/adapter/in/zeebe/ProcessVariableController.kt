package io.miragon.zeebe.taskmanager.adapter.`in`.zeebe

import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase.SaveCommand
import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase.UpdateCommand
import jakarta.persistence.EntityNotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/variable")
class ProcessVariableController(
    private val useCase: ProcessVariableUseCase,
)
{
    private val log = KotlinLogging.logger {}

    @PostMapping("/save")
    fun saveTask(@RequestBody job: JobRecord<ProcessVariable>): ResponseEntity<String>
    {
        val jobValue = job.value
        log.info { "Saving variable for instance ${jobValue.processInstanceKey} with name ${jobValue.name}." }

        try
        {
            useCase.save(
                SaveCommand(
                    processInstanceKey = jobValue.processInstanceKey,
                    bpmnProcessId = jobValue.bpmnProcessId,
                    processDefinitionKey = jobValue.processDefinitionKey,
                    name = jobValue.name,
                    value = unquoted(jobValue.value),
                )
            )
            return ResponseEntity.ok("Variable saved")
        } catch (e: IllegalArgumentException)
        {
            // other exceptions (e.g. database unavailable) lead to 500, so the exporter retries the record
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/update")
    fun updateTask(@RequestBody job: JobRecord<ProcessVariable>): ResponseEntity<String>
    {
        log.info { "Updating variable with key: ${job.key} ${job.intent}" }

        val value = job.value
        try
        {
            useCase.update(
                UpdateCommand(
                    processInstanceKey = value.processInstanceKey,
                    name = value.name,
                    value = unquoted(value.value),
                )
            )
            return ResponseEntity.ok("Variable updated")
        } catch (e: EntityNotFoundException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: IllegalArgumentException)
        {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    /**
     * Zeebe sends variable values as JSON, so a string value arrives with its quotes.
     */
    private fun unquoted(value: Any): Any = if (value is String) value.trim('"') else value
}
