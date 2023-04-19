package restaurant.showcase.waiter.adapter.out.process;

import io.miragon.miranum.connect.process.api.ProcessApi;
import io.miragon.miranum.connect.process.api.StartProcessCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutPort;

@AllArgsConstructor
@Component
public class ProcessAdapter implements PlaceOrderOutPort {

    private final ProcessApi processApi;

    @Override
    public void placeOrder(PlaceOrderOutCommand placeOrderOutCommand) {
        var startProcessCommand = new StartProcessCommand(placeOrderOutCommand.getProcessKey(), placeOrderOutCommand.getVariables());
        processApi.startProcess(startProcessCommand);
    }
}
