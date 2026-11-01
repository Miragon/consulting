package io.miragon.zeebe.taskmanager.adapter.`in`.zeebe

interface JobRecordValue
{
    val processInstanceKey: Long
    val bpmnProcessId: String
    val processDefinitionKey: Long
}

data class JobRecord<T : JobRecordValue>(
    val key: Long,
    val valueType: String,
    val intent: String,
    val value: T,
)

data class ProcessVariable(
    override val processInstanceKey: Long,
    override val bpmnProcessId: String,
    override val processDefinitionKey: Long,
    val name: String,
    val value: Any,
) : JobRecordValue

data class UserTask(
    override val processInstanceKey: Long,
    override val bpmnProcessId: String,
    override val processDefinitionKey: Long,
    val elementInstanceKey: Long,
    val processDefinitionVersion: Int,
    val elementId: String,
    // val customHeaders: Map<String, String>,
    // val externalFormReference: String,
    // val userTaskKey: Long,
    // val candidateGroupList: List<String>,
    // val candidateUserList: List<String>,
    val assignee: String,
) : JobRecordValue