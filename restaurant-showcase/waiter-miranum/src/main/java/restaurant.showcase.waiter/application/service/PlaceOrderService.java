package restaurant.showcase.waiter.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderUseCase;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderPort;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final PlaceOrderPort placeOrderPort;
    private final static String PROCESS_ID = "RestaurantMiranum";

    @Override
    public String placeOrder(PlaceOrderInCommand placeOrderInCommand) {
        final String orderId = String.format("order-%s-%s", placeOrderInCommand.getCustomerName(), LocalDateTime.now());
        var variables = variables(orderId, placeOrderInCommand);
        var placeOrderOutCommand = new PlaceOrderOutCommand(PROCESS_ID, variables);
        placeOrderPort.placeOrder(placeOrderOutCommand);
        return orderId;
    }

    private Map<String, Object> variables(String orderId, PlaceOrderInCommand placeOrderInCommand) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("orderId", orderId);
        variables.put("customerName", placeOrderInCommand.getCustomerName());
        variables.put("meal", placeOrderInCommand.getFood().getName());
        variables.put("diningOption", placeOrderInCommand.getDiningOption().getName());
        return variables;
    }
}
