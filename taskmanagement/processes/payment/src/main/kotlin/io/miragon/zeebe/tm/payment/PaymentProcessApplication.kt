package io.miragon.zeebe.tm.payment

import io.camunda.zeebe.spring.client.annotation.Deployment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@Deployment(resources = ["classpath:bpmn/*.bpmn"])
class PaymentProcessApplication

fun main(args: Array<String>)
{
    runApplication<PaymentProcessApplication>(*args)
}