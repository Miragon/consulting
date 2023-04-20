package restaurant.showcase.waiter.domain.events.order;

import restaurant.showcase.waiter.domain.Order;

public class MealPreparedEvent extends OrderDomainEvent {
    public MealPreparedEvent(Order order) {
        super(order);
    }
}
