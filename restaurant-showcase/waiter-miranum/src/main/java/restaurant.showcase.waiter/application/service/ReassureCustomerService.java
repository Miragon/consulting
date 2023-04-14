package restaurant.showcase.waiter.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.adapter.out.websocket.WebsocketNotificator;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerInCommand;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerUseCase;
import restaurant.showcase.waiter.domain.Order;

@Service
@AllArgsConstructor
public class ReassureCustomerService implements ReassureCustomerUseCase {

    private final WebsocketNotificator websocketNotificator;

    @Override
    public void reassureCustomer(ReassureCustomerInCommand reassureCustomerCommand) {
        var order = new Order(reassureCustomerCommand);
        var message = String.format("Hi %s, thanks for your patience.", order.getCustomerName());
        websocketNotificator.notify(order.getOrderId(), message);
    }
}
