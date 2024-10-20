package io.miragon.zeebe.taskmanager.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant

interface UserTaskRepository : JpaRepository<UserTaskEntity, Long>
{
    @Query("SELECT t FROM UserTaskEntity t WHERE t.expiresAt > :currentInstant AND t.taskState = 'CREATED'")
    fun findByExpiresAtAfter(@Param("currentInstant") currentInstant: Instant): List<UserTaskEntity>

    @Query("SELECT t FROM UserTaskEntity t WHERE t.taskState = :taskState")
    fun findByTaskState(taskState: String): List<UserTaskEntity>
}