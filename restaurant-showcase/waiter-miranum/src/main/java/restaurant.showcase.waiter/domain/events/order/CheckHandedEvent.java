package restaurant.showcase.waiter.domain.events.order;

import restaurant.showcase.waiter.domain.Order;

public class CheckHandedEvent extends OrderDomainEvent {

    public CheckHandedEvent(Order order) {
        super(order);
    }
}
