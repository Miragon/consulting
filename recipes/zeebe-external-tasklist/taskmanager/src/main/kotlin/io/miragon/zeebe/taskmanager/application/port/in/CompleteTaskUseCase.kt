package io.miragon.zeebe.taskmanager.application.port.`in`

interface CompleteTaskUseCase
{
    fun complete(command: Command): Boolean

    data class Command(
        val key: Long,
        val variables: Map<String, Any>?,
    )
}