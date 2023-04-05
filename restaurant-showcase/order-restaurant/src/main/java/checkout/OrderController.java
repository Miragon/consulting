package checkout;

import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@RestController
@AllArgsConstructor
public class OrderController {
    private final ZeebeClientLifecycle zeebeClient;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String handleOrder(@RequestBody OrderRO orderRO){

        System.out.printf("%s ordered %s to %s.%n", orderRO.getCustomerName(), orderRO.getMeal(), orderRO.getDiningOption());

        Map<String, String> vars = new HashMap<>();

        vars.put("businessKey", String.format("order-%s-%s", orderRO.getCustomerName(), LocalDate.now()));
        vars.put("customerName", orderRO.getCustomerName());
        vars.put("meal", orderRO.getMeal());
        vars.put("dining", orderRO.getDiningOption());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("RestaurantPlain")
                .latestVersion()
                .variables(vars)
                .send();

        String response = String.format("Thanks, %s! We'll prepare your %s to %s!", orderRO.getCustomerName(), orderRO.getMeal(), orderRO.getDiningOption());

        System.out.println(response);
        return response;
    }

}
