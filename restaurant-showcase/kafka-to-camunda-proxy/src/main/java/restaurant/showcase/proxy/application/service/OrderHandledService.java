package restaurant.showcase.proxy.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledInCommand;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledUseCase;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutCommand;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutPort;

@Service
@RequiredArgsConstructor
public class OrderHandledService implements OrderHandledUseCase {

    private final OrderHandledOutPort orderHandledOutPort;

    @Override
    public void onOrderHandled(OrderHandledInCommand orderHandledInCommand) {
        orderHandledOutPort.onOrderHandled(new OrderHandledOutCommand(orderHandledInCommand.getOrderId()));
    }
}
