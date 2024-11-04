package io.miragon.zeebe.taskmanager.application.port.out

interface CompleteTaskPort
{
    fun complete(taskId: Long, variables: Map<String, Any>?): Boolean
}
