package restaurant.showcase.waiter.domain.events.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import restaurant.showcase.waiter.domain.Order;
import restaurant.showcase.waiter.domain.events.DomainEvent;

@RequiredArgsConstructor
@Getter
public abstract class OrderDomainEvent extends DomainEvent {
    private final Order order;

    @Override
    public String getReferenceKey() {
        return order.getOrderId();
    }

}
