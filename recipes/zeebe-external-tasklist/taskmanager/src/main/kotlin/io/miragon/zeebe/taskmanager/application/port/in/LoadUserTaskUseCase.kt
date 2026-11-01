package io.miragon.zeebe.taskmanager.application.port.`in`

import io.miragon.zeebe.taskmanager.domain.UserTask

interface LoadUserTaskUseCase
{
    fun loadByExpirationDate(): List<UserTask>

    fun loadByTaskState(): List<UserTask>
}