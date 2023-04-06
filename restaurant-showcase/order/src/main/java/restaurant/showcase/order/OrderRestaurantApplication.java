package restaurant.showcase.order;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
public class OrderRestaurantApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderRestaurantApplication.class, args);
    }
}
