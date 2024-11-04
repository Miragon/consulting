package io.miragon.zeebe.taskmanager.adapter.`in`.rest.model

data class CompleteTaskCommand(
    val taskId: Long,
    val variables: Map<String, Any>?,
)