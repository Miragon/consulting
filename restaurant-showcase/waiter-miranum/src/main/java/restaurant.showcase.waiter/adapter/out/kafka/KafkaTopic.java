package restaurant.showcase.waiter.adapter.out.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import restaurant.showcase.waiter.domain.events.*;

@Getter
@RequiredArgsConstructor
public enum KafkaTopic {
    ORDER_PLACED("order-placed", OrderPlacedEvent.class),
    MEAL_PREPARED("meal-prepared", MealPreparedEvent.class),
    MEAL_PAYED("meal-payed", MealPayedEvent.class),
    MEAL_SERVED("meal-served", MealServedEvent.class),
    CUSTOMER_REASSURED("customer-reassured", CustomerReassuredEvent.class),
    CHECK_ISSUED("check-issued", CheckIssuedEvent.class),
    CHECK_HANDED("check-handed", CheckHandedEvent.class);

    private final String topicName;
    private final Class<? extends OrderDomainEvent> className;

    public static KafkaTopic fromClassName(Class<?> className) {
        for (KafkaTopic topic : KafkaTopic.values()) {
            if (topic.getClassName().equals(className)) {
                return topic;
            }
        }
        throw new IllegalArgumentException("No topic for class " + className);
    }
}
