package restaurant.showcase.waiter.application.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.in.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.PlaceOrderUseCase;
import restaurant.showcase.waiter.application.port.out.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.PlaceOrderPort;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@AllArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final PlaceOrderPort placeOrderPort;
    private final static String PROCESS_ID = "RestaurantMiranum";

    @Override
    public String placeOrder(PlaceOrderInCommand placeOrderInCommand) {
        final String orderId = String.format("order-%s-%s", placeOrderInCommand.getCustomerName(), LocalDateTime.now());
        Map<String, Object> variables = new ObjectMapper().convertValue(placeOrderInCommand, new TypeReference<>() {});
        var placeOrderOutCommand = new PlaceOrderOutCommand(PROCESS_ID, variables);
        placeOrderPort.placeOrder(placeOrderOutCommand);
        return orderId;
    }
}
