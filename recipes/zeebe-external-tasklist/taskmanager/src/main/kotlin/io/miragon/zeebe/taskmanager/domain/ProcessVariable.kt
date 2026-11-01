package io.miragon.zeebe.taskmanager.domain

data class ProcessVariable(
    val processInstanceKey: Long,
    val name: String,
    val value: Any,
    val bpmnProcessId: String?,
    val processDefinitionKey: Long?,
)