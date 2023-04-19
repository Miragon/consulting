package restaurant.showcase.waiter.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import restaurant.showcase.waiter.domain.Order;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class MealPreparedEvent extends OrderDomainEvent {
    private final Order order;

    @Override
    public HashMap<String, Object> convertValuesToMap() {
        return this.oMapper.convertValue(this.order, HashMap.class);
    }
}
