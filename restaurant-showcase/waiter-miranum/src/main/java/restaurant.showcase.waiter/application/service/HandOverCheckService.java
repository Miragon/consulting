package restaurant.showcase.waiter.application.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.application.port.in.handOverCheck.HandOverCheckInCommand;
import restaurant.showcase.waiter.application.port.in.handOverCheck.HandOverCheckUseCase;
import restaurant.showcase.waiter.application.port.out.notifyCustomer.NotifyCustomerOutCommand;
import restaurant.showcase.waiter.application.port.out.notifyCustomer.NotifyCustomerOutPort;
import restaurant.showcase.waiter.domain.DiningOption;
import restaurant.showcase.waiter.domain.Order;
import restaurant.showcase.waiter.domain.events.order.CheckHandedEvent;

@Service
@AllArgsConstructor
public class HandOverCheckService implements HandOverCheckUseCase {

    private final NotifyCustomerOutPort notifyCustomerOutPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void handOverCheck(HandOverCheckInCommand handOverCheckInCommand) {
        var order = new Order(handOverCheckInCommand);
        String message;
        if (order.getDiningOption() == DiningOption.DINE_IN) {
            message = String.format("We received your payment, %s. Thanks for having a %s at our place!", order.getCustomerName(), order.getFood().getName());
        } else {
            message = String.format("We finished your %s and received your payment, %s. Thanks for ordering at our place!", order.getFood().getName(), order.getCustomerName());
        }
        notifyCustomerOutPort.notifyCustomer(new NotifyCustomerOutCommand(order.getOrderId(), message));
        applicationEventPublisher.publishEvent(new CheckHandedEvent(order));
    }
}
