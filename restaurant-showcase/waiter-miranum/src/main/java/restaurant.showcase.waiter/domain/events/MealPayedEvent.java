package restaurant.showcase.waiter.domain.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MealPayedEvent extends DomainEvent {
    private final String orderId;

    @Override
    public String getReferenceKey() {
        return orderId;
    }
}
