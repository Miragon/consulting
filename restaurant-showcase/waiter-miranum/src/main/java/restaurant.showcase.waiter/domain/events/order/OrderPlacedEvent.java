package restaurant.showcase.waiter.domain.events.order;

import restaurant.showcase.waiter.domain.Order;

public class OrderPlacedEvent extends OrderDomainEvent {
    public OrderPlacedEvent(Order order) {
        super(order);
    }
}
