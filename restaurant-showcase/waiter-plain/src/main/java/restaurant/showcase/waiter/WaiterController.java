package restaurant.showcase.waiter;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import restaurant.showcase.waiter.model.OrderRequestDto;
import restaurant.showcase.waiter.model.OrderResponseDto;
import restaurant.showcase.waiter.model.PayRequestDto;
import restaurant.showcase.waiter.model.PayResponseDto;
import restaurant.showcase.waiter.websocket.WebsocketNotificationListener;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class WaiterController {

    private final ZeebeClient zeebeClient;
    private final WebsocketNotificationListener websocketNotificationListener;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<OrderResponseDto> handleOrder(@RequestBody OrderRequestDto orderRequestDto){
        String orderId = String.format("order-%s-%s", orderRequestDto.getCustomerName(), LocalDateTime.now());
        log.info("[{}] {} ordered a {} to {}.", orderId, orderRequestDto.getCustomerName(), orderRequestDto.getMeal(), orderRequestDto.getDiningOption());

        Map<String, String> vars = new HashMap<>();
        vars.put("orderId", orderId);
        vars.put("customerName", orderRequestDto.getCustomerName());
        vars.put("meal", orderRequestDto.getMeal());
        vars.put("diningOption", orderRequestDto.getDiningOption());

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("RestaurantPlain")
                .latestVersion()
                .variables(vars)
                .send();

        return ResponseEntity.ok(new OrderResponseDto(
                orderId,
                String.format("Thanks, %s! We'll prepare your %s to %s!", orderRequestDto.getCustomerName(), orderRequestDto.getMeal(), orderRequestDto.getDiningOption())
        ));
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity<PayResponseDto> handlePay(@RequestBody PayRequestDto payRequestDto){

        zeebeClient.newPublishMessageCommand()
                .messageName("ReadyToPay")
                .correlationKey(payRequestDto.getOrderId())
                .send();

        String response = "Payment handled. Thank you.";
        log.info("[{}] {}", payRequestDto.getOrderId(), response);
        return ResponseEntity.ok(new PayResponseDto(response));
    }

    @RequestMapping(value = "/serve", method = RequestMethod.POST)
    public void serveMeal(@RequestBody OrderRequestDto orderRequestDto) {
        String response = String.format("Here is your %s, %s!", orderRequestDto.getMeal(), orderRequestDto.getCustomerName());
        log.info("[{}] {}", orderRequestDto.getOrderId(), response);
        websocketNotificationListener.notify(orderRequestDto.getOrderId(), response);
    }

    @RequestMapping(value = "/ready", method = RequestMethod.POST)
    public void payment(@RequestBody OrderRequestDto orderRequestDto) {
        String response;
        if (orderRequestDto.getDiningOption().equals("dine-in")) {
            response = String.format("We received your payment, %s. Thanks for having a %s at our place!", orderRequestDto.getCustomerName(), orderRequestDto.getMeal());
        } else {
            response = String.format("We finished your %s and received your payment, %s. Thanks for ordering at our place!", orderRequestDto.getMeal(), orderRequestDto.getCustomerName());
        }
        log.info("[{}] {}", orderRequestDto.getOrderId(), response);
        websocketNotificationListener.notify(orderRequestDto.getOrderId(), response);
    }

    @RequestMapping(value = "/calm", method = RequestMethod.POST)
    public void calm(@RequestBody OrderRequestDto orderRequestDto) {
        String response = String.format("Jo %s, calm down! I'm with you in a second!", orderRequestDto.getCustomerName());
        log.info("[{}] {}", orderRequestDto.getOrderId(), response);
        websocketNotificationListener.notify(orderRequestDto.getOrderId(), response);
    }
}
