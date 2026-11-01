package io.miragon.zeebe.taskmanager.adapter.`in`.rest.model

import io.miragon.zeebe.taskmanager.application.port.`in`.LoadMetadataUseCase.ProcessApplication

data class MetadataDto(
    val processApplications: List<ProcessApplication>
)