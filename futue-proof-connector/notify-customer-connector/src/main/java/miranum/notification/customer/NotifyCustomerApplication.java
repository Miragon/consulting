package miranum.notification.customer;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotifyCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotifyCustomerApplication.class, args);
    }
}
