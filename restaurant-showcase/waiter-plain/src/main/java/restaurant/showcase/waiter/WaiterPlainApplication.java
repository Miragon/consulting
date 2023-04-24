package restaurant.showcase.waiter;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath*:**/restaurant-plain.bpmn")
public class WaiterPlainApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaiterPlainApplication.class, args);
    }
}