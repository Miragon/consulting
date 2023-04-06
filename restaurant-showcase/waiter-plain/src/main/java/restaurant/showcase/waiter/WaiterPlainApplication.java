package restaurant.showcase.waiter;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class WaiterPlainApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaiterPlainApplication.class, args);
    }
}
