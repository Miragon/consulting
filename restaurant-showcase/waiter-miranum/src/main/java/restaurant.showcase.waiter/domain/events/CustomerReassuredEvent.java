package restaurant.showcase.waiter.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import restaurant.showcase.waiter.domain.Order;

@AllArgsConstructor
@Getter
public class CustomerReassuredEvent {
    private final Order order;
}
