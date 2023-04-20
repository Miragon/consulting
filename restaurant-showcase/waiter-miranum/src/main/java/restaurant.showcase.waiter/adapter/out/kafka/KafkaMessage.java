package restaurant.showcase.waiter.adapter.out.kafka;

import restaurant.showcase.waiter.domain.events.DomainEvent;
import restaurant.showcase.waiter.domain.events.order.OrderDomainEvent;

import java.util.HashMap;

public class KafkaMessage {
    private final String eventName;
    private final String eventReferenceKey;
    private final HashMap<String, Object> eventValues;

    public KafkaMessage(DomainEvent domainEvent) {
        this.eventName = domainEvent.getClass().getSimpleName();
        this.eventReferenceKey = domainEvent.getReferenceKey();
        this.eventValues = new HashMap<>();
    }

    public KafkaMessage(OrderDomainEvent orderDomainEvent) {
        this.eventName = orderDomainEvent.getClass().getSimpleName();
        this.eventReferenceKey = orderDomainEvent.getReferenceKey();
        this.eventValues = orderDomainEvent.getOrder().asHashMap();
    }

    public HashMap<String, Object> getValues() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("eventName", eventName);
        map.put("eventReferenceKey", eventReferenceKey);
        map.putAll(eventValues);
        return map;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventReferenceKey() {
        return eventReferenceKey;
    }
}
