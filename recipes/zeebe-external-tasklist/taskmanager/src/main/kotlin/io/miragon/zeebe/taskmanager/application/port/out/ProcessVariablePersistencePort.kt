package io.miragon.zeebe.taskmanager.application.port.out

import io.miragon.zeebe.taskmanager.domain.ProcessVariable

interface ProcessVariablePersistencePort
{
    fun findByProcessInstanceKey(processInstanceKey: Long): List<ProcessVariable>

    fun findByName(processInstanceKey: Long, name: String): ProcessVariable

    fun save(processVariable: ProcessVariable): Long

    fun update(updatedProcessVariable: ProcessVariable): Long
}