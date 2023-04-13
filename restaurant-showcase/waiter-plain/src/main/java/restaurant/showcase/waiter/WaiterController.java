package restaurant.showcase.waiter;

import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import restaurant.showcase.waiter.model.OrderRO;
import restaurant.showcase.waiter.model.OrderResponseRO;
import restaurant.showcase.waiter.model.PayRO;
import restaurant.showcase.waiter.model.PayResponseRO;
import restaurant.showcase.waiter.websocket.WebsocketNotificationListener;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class WaiterController {

    private final ZeebeClientLifecycle zeebeClient;
    private final WebsocketNotificationListener websocketNotificationListener;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<OrderResponseRO> handleOrder(@RequestBody OrderRO orderRO){
        String orderId = String.format("order-%s-%s", orderRO.getCustomerName(), LocalDateTime.now());
        log.info("[{}] {} ordered a {} to {}.", orderId, orderRO.getCustomerName(), orderRO.getMeal(), orderRO.getDiningOption());

        Map<String, String> vars = new HashMap<>();
        vars.put("orderId", orderId);
        vars.put("customerName", orderRO.getCustomerName());
        vars.put("meal", orderRO.getMeal());
        vars.put("diningOption", orderRO.getDiningOption());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("RestaurantPlain")
                .latestVersion()
                .variables(vars)
                .send();

        return ResponseEntity.ok(new OrderResponseRO(
                orderId,
                String.format("Thanks, %s! We'll prepare your %s to %s!", orderRO.getCustomerName(), orderRO.getMeal(), orderRO.getDiningOption())
        ));
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity<PayResponseRO> handleOrder(@RequestBody PayRO payRO){

        zeebeClient.newPublishMessageCommand()
                .messageName("Customer wants to pay")
                .correlationKey(payRO.getOrderId())
                .send();

        String response = "Payment handled. Thank you.";
        log.info("[{}] {}", payRO.getOrderId(), response);
        return ResponseEntity.ok(new PayResponseRO(response));
    }

    @RequestMapping(value = "/calm/customer", method = RequestMethod.POST)
    public void payment(@RequestBody OrderRO orderRO) {
        String response = String.format("Jo %s, calm down! I'm with you in a second!", orderRO.getCustomerName());
        log.info("[{}] {}", orderRO.getOrderId(), response);
        websocketNotificationListener.notify(orderRO.getOrderId(), response);
    }

    @RequestMapping(value = "/meal/serve", method = RequestMethod.POST)
    public void serveMeal(@RequestBody OrderRO orderRO) {
        String response = String.format("Here is your %s, %s!", orderRO.getMeal(), orderRO.getCustomerName());
        log.info("[{}] {}", orderRO.getOrderId(), response);
        websocketNotificationListener.notify(orderRO.getOrderId(), response);
    }
}
