package restaurant.showcase.waiter.adapter.out.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import restaurant.showcase.waiter.domain.events.*;
import restaurant.showcase.waiter.domain.events.order.*;

@Getter
@RequiredArgsConstructor
public enum KafkaTopic {
    ORDER_PLACED("restaurant-order-customer", OrderPlacedEvent.class),
    CUSTOMER_REASSURED("restaurant-order-customer", CustomerReassuredEvent.class),
    MEAL_PREPARED("restaurant-order-meal", MealPreparedEvent.class),
    MEAL_PAYED("restaurant-order-meal", MealPayedEvent.class),
    MEAL_SERVED("restaurant-order-meal", MealServedEvent.class),
    CHECK_ISSUED("restaurant-order-check", CheckIssuedEvent.class),
    CHECK_HANDED("restaurant-order-check", CheckHandedEvent.class);

    private final String topicName;
    private final Class<? extends DomainEvent> className;

    public static KafkaTopic fromClassName(Class<? extends DomainEvent> className) {
        for (KafkaTopic topic : KafkaTopic.values()) {
            if (topic.getClassName().equals(className)) {
                return topic;
            }
        }
        throw new IllegalArgumentException("No topic for class " + className);
    }
}
