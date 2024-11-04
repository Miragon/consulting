package io.miragon.zeebe.taskmanager.application.port.`in`

interface ProcessVariableUseCase
{
    fun save(command: SaveCommand)

    fun update(command: UpdateCommand)

    data class SaveCommand(
        val processInstanceKey: Long,
        val bpmnProcessId: String,
        val processDefinitionKey: Long,
        val name: String,
        val value: Any,
    )

    data class UpdateCommand(
        val processInstanceKey: Long,
        val name: String,
        val value: Any,
    )
}