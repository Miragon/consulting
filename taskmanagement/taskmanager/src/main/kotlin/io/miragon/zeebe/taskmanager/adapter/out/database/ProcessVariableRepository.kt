package io.miragon.zeebe.taskmanager.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProcessVariableRepository : JpaRepository<ProcessVariableEntity, Long>
{
    @Query("SELECT v FROM ProcessVariableEntity v WHERE v.processInstanceKey = :processInstanceKey")
    fun findByProcessInstanceKey(processInstanceKey: Long): List<ProcessVariableEntity>

    @Query("SELECT v FROM ProcessVariableEntity v WHERE v.processInstanceKey = :processInstanceKey AND v.name = :name")
    fun findByName(processInstanceKey: Long, name: String): ProcessVariableEntity
}
