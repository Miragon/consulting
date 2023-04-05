package pizza.async.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Random;

@Component
@AllArgsConstructor
public class PizzaOrderService implements OrderService {
    private final RestTemplate restTemplate;

    @JobWorker(type = "sortVinoOrder", fetchAllVariables = true)
    public void execute(@Variable String customerName, @Variable String orderMessage) throws Exception {
        Random random = new Random();

        if(orderMessage.contains("hawaii") && random.nextBoolean()){
            Thread.sleep(10000);
            throw new ZeebeBpmnError("DOESNT_WORK", "We do not have any pineapples");
        } else {
            if (orderMessage.toLowerCase().contains("pizza/async")) {
                restTemplate.put(URI.create("http://localhost:8081/sortOrder/"), new OrderRO(orderMessage, ""));
                System.out.println("I've sent an order for Pizza");
            }
        }
    }
}

