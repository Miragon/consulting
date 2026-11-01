package io.miragon.zeebe.taskmanager.adapter.out.database

import io.miragon.zeebe.taskmanager.application.port.out.ProcessVariablePersistencePort
import io.miragon.zeebe.taskmanager.domain.ProcessVariable
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component

@Component
class ProcessVariablePersistenceAdapter(
    private val repository: ProcessVariableRepository
) : ProcessVariablePersistencePort
{
    override fun findByProcessInstanceKey(
        processInstanceKey: Long,
    ): List<ProcessVariable>
    {
        return repository.findByProcessInstanceKey(processInstanceKey).map { it.toDomain() }
    }

    override fun findByName(
        processInstanceKey: Long,
        name: String,
    ): ProcessVariable
    {
        return findExisting(processInstanceKey, name).toDomain()
    }

    override fun save(processVariable: ProcessVariable): Long
    {
        // The exporter delivers at least once: a record that is delivered again must not create a second row.
        val existingProcessVariable = repository.findByName(processVariable.processInstanceKey, processVariable.name)
        if (existingProcessVariable != null)
        {
            existingProcessVariable.value = processVariable.value
            return repository.save(existingProcessVariable).processInstanceKey
        }

        val entity = ProcessVariableEntity(
            processInstanceKey = processVariable.processInstanceKey,
            bpmnProcessId = processVariable.bpmnProcessId ?: "",
            processDefinitionKey = processVariable.processDefinitionKey ?: Long.MIN_VALUE,
            name = processVariable.name,
            value = processVariable.value,
        )

        return repository.save(entity).processInstanceKey
    }

    override fun update(updatedProcessVariable: ProcessVariable): Long
    {
        val existingProcessVariable = findExisting(
            updatedProcessVariable.processInstanceKey, updatedProcessVariable.name
        )

        existingProcessVariable.value = updatedProcessVariable.value

        return repository.save(existingProcessVariable).processInstanceKey
    }

    private fun findExisting(processInstanceKey: Long, name: String): ProcessVariableEntity
    {
        return repository.findByName(processInstanceKey, name)
            ?: throw EntityNotFoundException("Variable $name of process instance $processInstanceKey not found")
    }
}