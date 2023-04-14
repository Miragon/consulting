package restaurant.showcase.waiter.application.service;

import lombok.RequiredArgsConstructor;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderUseCase;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderPort;
import restaurant.showcase.waiter.domain.Order;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final PlaceOrderPort placeOrderPort;
    private final static String PROCESS_ID = "RestaurantMiranum";

    @Override
    public String placeOrder(PlaceOrderInCommand placeOrderInCommand) {
        var order = new Order(placeOrderInCommand);
        var variables = variables(order);
        var placeOrderOutCommand = new PlaceOrderOutCommand(PROCESS_ID, variables);
        placeOrderPort.placeOrder(placeOrderOutCommand);
        return order.getOrderId();
    }

    private Map<String, Object> variables(Order order) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("orderId", order.getOrderId());
        variables.put("customerName", order.getCustomerName());
        variables.put("meal", order.getFood().getName());
        variables.put("diningOption", order.getDiningOption().getName());
        return variables;
    }
}
