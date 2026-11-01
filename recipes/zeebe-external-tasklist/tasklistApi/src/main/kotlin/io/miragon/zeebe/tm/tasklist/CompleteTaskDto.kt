package io.miragon.zeebe.tm.tasklist

data class CompleteTaskDto<T : FormData>(
    val userTask: UserTaskDto,
    val formData: T
)
