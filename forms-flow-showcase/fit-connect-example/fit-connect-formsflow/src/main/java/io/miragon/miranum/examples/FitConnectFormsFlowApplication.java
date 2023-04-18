package io.miragon.miranum.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FitConnectFormsFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitConnectFormsFlowApplication.class, args);
    }
}
