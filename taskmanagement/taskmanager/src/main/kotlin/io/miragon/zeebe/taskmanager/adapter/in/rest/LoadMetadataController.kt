package io.miragon.zeebe.taskmanager.adapter.`in`.rest

import io.miragon.zeebe.taskmanager.adapter.`in`.rest.model.MetadataDto
import io.miragon.zeebe.taskmanager.application.port.`in`.LoadMetadataUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class LoadMetadataController(
    private val loadMetadataUseCase: LoadMetadataUseCase
)
{
    @GetMapping("/metadata")
    fun loadMetadata(): ResponseEntity<MetadataDto>
    {
        val processApplications = loadMetadataUseCase.load().processApplications
        return ResponseEntity.ok(MetadataDto(processApplications))
    }
}
