package io.miragon.zeebe.taskmanager.adapter.out.database

import io.miragon.zeebe.taskmanager.application.port.out.ProcessVariablePersistencePort
import io.miragon.zeebe.taskmanager.domain.ProcessVariable
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
        return repository.findByName(processInstanceKey, name).toDomain()
    }

    override fun save(processVariable: ProcessVariable): Long
    {
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
        val existingProcessVariable = repository.findByName(
            updatedProcessVariable.processInstanceKey, updatedProcessVariable.name
        )

        existingProcessVariable.value = updatedProcessVariable.value

        return repository.save(existingProcessVariable).processInstanceKey
    }
}