package restaurant.showcase.waiter.domain.events.order;

import restaurant.showcase.waiter.domain.Order;

public class MealServedEvent extends OrderDomainEvent {
    public MealServedEvent(Order order) {
        super(order);
    }
}
