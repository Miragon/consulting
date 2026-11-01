package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase.SaveCommand
import io.miragon.zeebe.taskmanager.application.port.`in`.ProcessVariableUseCase.UpdateCommand
import io.miragon.zeebe.taskmanager.application.port.out.ProcessVariablePersistencePort
import io.miragon.zeebe.taskmanager.domain.ProcessVariable
import org.springframework.stereotype.Service

@Service
class ProcessVariableService(
    val persistencePort: ProcessVariablePersistencePort,
) : ProcessVariableUseCase
{
    override fun save(command: SaveCommand)
    {
        val processVariable = ProcessVariable(
            processInstanceKey = command.processInstanceKey,
            bpmnProcessId = command.bpmnProcessId,
            processDefinitionKey = command.processDefinitionKey,
            name = command.name,
            value = command.value,
        )

        persistencePort.save(processVariable)
    }

    override fun update(command: UpdateCommand)
    {
        val processVariable = ProcessVariable(
            processInstanceKey = command.processInstanceKey,
            name = command.name,
            value = command.value,
            bpmnProcessId = null,
            processDefinitionKey = null,
        )

        persistencePort.update(processVariable)
    }
}