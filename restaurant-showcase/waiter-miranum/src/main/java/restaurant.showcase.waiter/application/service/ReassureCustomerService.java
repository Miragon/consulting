package restaurant.showcase.waiter.application.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.adapter.out.websocket.WebsocketNotificator;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerInCommand;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerUseCase;
import restaurant.showcase.waiter.application.port.out.notifyCustomer.NotifyCustomerOutCommand;
import restaurant.showcase.waiter.application.port.out.notifyCustomer.NotifyCustomerOutPort;
import restaurant.showcase.waiter.domain.Order;
import restaurant.showcase.waiter.domain.events.CustomerReassuredEvent;

@Service
@AllArgsConstructor
public class ReassureCustomerService implements ReassureCustomerUseCase {

    private final NotifyCustomerOutPort notifyCustomerOutPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void reassureCustomer(ReassureCustomerInCommand reassureCustomerCommand) {
        var order = new Order(reassureCustomerCommand);
        var message = String.format("Hi %s, thanks for your patience.", order.getCustomerName());
        notifyCustomerOutPort.notifyCustomer(new NotifyCustomerOutCommand(order.getOrderId(), message));
        applicationEventPublisher.publishEvent(new CustomerReassuredEvent(order));
    }
}
