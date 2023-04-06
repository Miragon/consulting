package restaurant.showcase.waiter.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class PizzaOrderService implements OrderService {
    private final RestTemplate restTemplate;

    public PizzaOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void execute(OrderRO orderRequest) {
        System.out.println("Handing pizza order to kitchen!");
        restTemplate.postForLocation(URI.create("http://localhost:8081/pizza/bake"), orderRequest);
    }
}
