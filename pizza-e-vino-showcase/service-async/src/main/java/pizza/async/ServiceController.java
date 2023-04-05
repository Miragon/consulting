package pizza.async;

import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pizza.async.service.OrderRO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ServiceController {

    private ZeebeClientLifecycle zeebeClient;

    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public String handleOrder(@RequestBody OrderRO orderRO){

        System.out.printf("Got this order for %s: %s %n", orderRO.getCustomerName(), orderRO.getOrderMessage());

        Map<String, String> vars = new HashMap<>();

        vars.put("orderMessage", orderRO.getOrderMessage().toLowerCase());
        vars.put("businessKey", String.format("order-%s-%s", orderRO.getOrderMessage(), LocalDate.now()));


        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("ProcessOrder")
                .latestVersion()
                .variables(vars)
                .send();

        String response = String.format("Hi, %s!%s", orderRO.getCustomerName(), " Your order is being processed");

        System.out.println(response);
        return response;
    }
}
