package restaurant.showcase.waiter.domain.events.order;

import restaurant.showcase.waiter.domain.Order;

public class CustomerReassuredEvent extends OrderDomainEvent {
    public CustomerReassuredEvent(Order order) {
        super(order);
    }
}
