package restaurant.showcase.waiter.domain.events.order;

import restaurant.showcase.waiter.domain.Order;

public class CheckIssuedEvent extends OrderDomainEvent {
    public CheckIssuedEvent(Order order) {
        super(order);
    }
}
