package restaurant.showcase.proxy.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledInCommand;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledUseCase;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutCommand;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutPort;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderHandledService implements OrderHandledUseCase {

    private final OrderHandledOutPort orderHandledOutPort;

    @Override
    public void onOrderHandled(OrderHandledInCommand orderHandledInCommand) {
        String correlationKey = orderHandledInCommand.getVariables().get("orderId").toString();
        orderHandledOutPort.onOrderHandled(new OrderHandledOutCommand(correlationKey, orderHandledInCommand.getVariables()));
        log.info("Order handled event received for order with id: {}", correlationKey);
    }
}
