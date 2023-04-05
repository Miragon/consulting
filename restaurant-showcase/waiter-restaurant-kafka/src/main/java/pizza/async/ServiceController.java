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

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String handleOrder(@RequestBody OrderRO orderRO){

        System.out.printf("Got this order for %s: Pizza %s, Vino %s %n", orderRO.getCustomerName(), orderRO.getPizza(), orderRO.getVino());

        Map<String, String> vars = new HashMap<>();

        vars.put("pizza", orderRO.getPizza());
        vars.put("vino", orderRO.getPizza());
        vars.put("customerName", orderRO.getCustomerName());
        vars.put("businessKey", String.format("order-%s-%s", orderRO.getCustomerName(), LocalDate.now()));

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
