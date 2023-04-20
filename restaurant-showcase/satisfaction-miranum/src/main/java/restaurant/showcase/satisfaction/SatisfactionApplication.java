package restaurant.showcase.satisfaction;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class SatisfactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SatisfactionApplication.class, args);
    }
}
