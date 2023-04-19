package restaurant.showcase.waiter.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class MealPayedEvent extends OrderDomainEvent {
    private final String orderId;

    @Override
    public HashMap<String, Object> convertValuesToMap() {
        return this.oMapper.convertValue(this.orderId, HashMap.class);
    }
}
