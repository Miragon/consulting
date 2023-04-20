package restaurant.showcase.waiter.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderUseCase;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutCommand;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderOutPort;
import restaurant.showcase.waiter.domain.Order;
import restaurant.showcase.waiter.domain.events.order.OrderPlacedEvent;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final PlaceOrderOutPort placeOrderPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public String placeOrder(PlaceOrderInCommand placeOrderInCommand) {
        var order = new Order(placeOrderInCommand);
        var placeOrderOutCommand = new PlaceOrderOutCommand(order);
        placeOrderPort.placeOrder(placeOrderOutCommand);
        applicationEventPublisher.publishEvent(new OrderPlacedEvent(order));
        return order.getOrderId();
    }
}
