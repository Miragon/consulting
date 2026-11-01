package io.miragon.zeebe.taskmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class TaskmanagerApplication

fun main(args: Array<String>)
{
    runApplication<TaskmanagerApplication>(*args)
}