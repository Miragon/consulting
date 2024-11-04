package io.miragon.zeebe.taskmanager.domain

import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "miranum.tm.metadata")
data class ProcessApplicationMetadata(
    val processApplication: Map<String, ProcessApplication>,
)
{
    companion object
    {
        fun buildUrl(baseUrl: String, path: String) = "$baseUrl$path"
    }

    data class ProcessApplication(
        @get:NotBlank
        val label: String,

        @get:NotBlank
        @get:Pattern(regexp = "^https?://.+:[0-9]")
        val baseUrl: String,

        val startable: Boolean = true,

        @get:Nullable
        val processUrl: Process = Process(),

        @get:Nullable
        val userTaskUrl: UserTask = UserTask(),
    )

    data class Process(
        @get:Pattern(regexp = "^/.*")
        val start: String = "/rest/process/start",

        @get:Pattern(regexp = "^/.*")
        val form: String = "/rest/process/start/form",
    )

    data class UserTask(
        @get:Pattern(regexp = "^/.*")
        val load: String = "/rest/task/load",

        @get:Pattern(regexp = "^/.*")
        val complete: String = "/rest/task/complete",

        @get:Pattern(regexp = "^/.*")
        val update: String = "/rest/task/update"
    )
}
