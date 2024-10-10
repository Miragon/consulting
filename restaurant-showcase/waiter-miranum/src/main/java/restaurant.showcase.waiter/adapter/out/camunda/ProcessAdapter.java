package restaurant.showcase.waiter.adapter.out.camunda;

import io.miragon.miranum.connect.process.api.ProcessApi;
import io.miragon.miranum.connect.process.api.StartProcessCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutPort;

@Slf4j
@Component
@AllArgsConstructor
public class ProcessAdapter implements PlaceOrderOutPort {

    private final ProcessApi processApi;
    private final static String PLACE_ORDER_PROCESS_ID = "RestaurantMiranum";

    @Override
    public void placeOrder(PlaceOrderOutCommand placeOrderOutCommand) {
        var startProcessCommand = new StartProcessCommand(PLACE_ORDER_PROCESS_ID, placeOrderOutCommand.getOrder().asHashMap());
        processApi.startProcess(startProcessCommand);
        log.info("[{}] Started Process {} with variables {}", startProcessCommand.getProcessKey(), PLACE_ORDER_PROCESS_ID, startProcessCommand.getVariables());
    }
}
