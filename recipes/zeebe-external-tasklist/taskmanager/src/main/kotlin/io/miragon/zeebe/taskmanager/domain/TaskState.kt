package io.miragon.zeebe.taskmanager.domain

enum class TaskState
{
    CREATED,
    COMPLETED,
    CANCELED,
    TIMED_OUT,
}