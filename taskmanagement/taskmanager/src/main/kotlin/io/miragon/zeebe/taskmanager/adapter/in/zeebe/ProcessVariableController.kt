package io.miragon.zeebe.taskmanager.adapter.`in`.zeebe

import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase.SaveCommand
import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase.UpdateCommand
import mu.KotlinLogging
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

        val variableValue = if (jobValue.value is String)
        {
            jobValue.value.trim('"')
        } else
        {
            jobValue.value
        }

        try
        {
            useCase.save(
                SaveCommand(
                    processInstanceKey = jobValue.processInstanceKey,
                    bpmnProcessId = jobValue.bpmnProcessId,
                    processDefinitionKey = jobValue.processDefinitionKey,
                    name = jobValue.name,
                    value = variableValue,
                )
            )
            return ResponseEntity.ok("Variable saved")
        } catch (e: Exception)
        {
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
                    processInstanceKey = job.key,
                    name = value.name,
                    value = value.value,
                )
            )
            return ResponseEntity.ok("Variable updated")
        } catch (e: Exception)
        {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}
