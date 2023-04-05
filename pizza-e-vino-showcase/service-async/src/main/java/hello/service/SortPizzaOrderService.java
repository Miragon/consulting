package hello.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Random;

@Component(value = "sortPizzaOrder")
public class SortPizzaOrderService {
    private final RestTemplate restTemplate;

    public SortPizzaOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @JobWorker(type = "sortPizzaOrder", fetchAllVariables = true)
    public void sortPizzaOrder(@Variable String orderMessage) throws Exception {
        Random random = new Random();

        if(orderMessage.contains("hawaii") && random.nextBoolean()){
            Thread.sleep(10000);
            throw new ZeebeBpmnError("DOESNT_WORK", "We do not have any pineapples");
        } else {
            if (orderMessage.toLowerCase().contains("pizza")) {
                URI uri = URI.create("http://localhost:8081/WorkIt/");
                OrderMessageRequest request = new OrderMessageRequest();
                request.orderMessage = orderMessage;
                restTemplate.put(uri, request);
                System.out.println("I've sent an order for Pizza");
            }
        }
    }
}
