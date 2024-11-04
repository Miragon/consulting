package io.miragon.zeebe.taskmanager.application.port.`in`

import java.time.Instant

interface UserTaskUseCase
{
    fun sync(command: SyncCommand)

    fun updateState(key: Long, state: String)

    fun save(command: SaveCommand)

    data class SaveCommand(
        val key: Long,
        val elementId: String,
        val processInstanceKey: Long,
        val bpmnProcessId: String,
        val processDefinitionKey: Long,
        val assignee: String,
    )

    data class SyncCommand(
        val key: Long,
        val elementId: String,
        val processInstanceKey: Long,
        val bpmnProcessId: String,
        val processDefinitionKey: Long,
        val variables: Map<String, Any>,
        val expiresAt: Instant,
    )
}
