package restaurant.showcase.waiter.adapter.out.process;

import io.miragon.miranum.connect.process.api.ProcessApi;
import io.miragon.miranum.connect.process.api.StartProcessCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutPort;
import restaurant.showcase.waiter.domain.Order;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class ProcessAdapter implements PlaceOrderOutPort {

    private final ProcessApi processApi;

    private final static String PROCESS_ID = "RestaurantMiranum";

    @Override
    public void placeOrder(PlaceOrderOutCommand placeOrderOutCommand) {
        var startProcessCommand = new StartProcessCommand(PROCESS_ID, this.variables(placeOrderOutCommand.getOrder()));
        processApi.startProcess(startProcessCommand);
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
