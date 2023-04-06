package restaurant.showcase.waiter;

import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class WaiterController {

    private final ZeebeClientLifecycle zeebeClient;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String handleOrder(@RequestBody OrderRO orderRO){

        log.info("{} ordered a {} to {}.", orderRO.getCustomerName(), orderRO.getMeal(), orderRO.getDiningOption());

        Map<String, String> vars = new HashMap<>();

        vars.put("businessKey", String.format("order-%s-%s", orderRO.getCustomerName(), LocalDate.now()));
        vars.put("customerName", orderRO.getCustomerName());
        vars.put("meal", orderRO.getMeal());
        vars.put("diningOption", orderRO.getDiningOption());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("RestaurantPlain")
                .latestVersion()
                .variables(vars)
                .send();

        return String.format("Thanks, %s! We'll prepare your %s to %s!", orderRO.getCustomerName(), orderRO.getMeal(), orderRO.getDiningOption());
    }

    @RequestMapping(value = "/calm/customer", method = RequestMethod.POST)
    public void payment(@RequestBody OrderRO orderRO) {
        log.info("Jo {}, calm down! Your order is on its way!", orderRO.getCustomerName());
    }

    @RequestMapping(value = "/meal/serve", method = RequestMethod.POST)
    public void serveMeal(@RequestBody OrderRO orderRO) {
        log.info("Here is your {}, {}!", orderRO.getMeal(), orderRO.getCustomerName());
    }
}
