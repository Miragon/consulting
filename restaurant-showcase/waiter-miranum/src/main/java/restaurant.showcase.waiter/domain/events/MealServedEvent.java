package restaurant.showcase.waiter.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import restaurant.showcase.waiter.domain.Order;

@AllArgsConstructor
@Getter
public class MealServedEvent {
    private final Order order;
}
