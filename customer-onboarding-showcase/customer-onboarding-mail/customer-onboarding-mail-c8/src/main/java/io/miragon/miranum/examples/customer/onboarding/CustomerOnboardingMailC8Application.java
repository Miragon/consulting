package io.miragon.miranum.examples.customer.onboarding;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class CustomerOnboardingMailC8Application {

    public static void main(String[] args) {
        SpringApplication.run(CustomerOnboardingMailC8Application.class, args);
    }
}
