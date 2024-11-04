package io.miragon.zeebe.tm.order

import io.camunda.zeebe.spring.client.annotation.Deployment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@Deployment(resources = ["classpath:bpmn/*.bpmn"])
class OrderProcessApplication

fun main(args: Array<String>)
{
    runApplication<OrderProcessApplication>(*args)
}