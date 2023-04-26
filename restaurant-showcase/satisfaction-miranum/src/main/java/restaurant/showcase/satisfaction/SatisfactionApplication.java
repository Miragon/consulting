package restaurant.showcase.satisfaction;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath*:**/satisfaction-miranum.bpmn")
public class SatisfactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SatisfactionApplication.class, args);
    }
}