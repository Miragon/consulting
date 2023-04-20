package restaurant.showcase.waiter.adapter.out.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import restaurant.showcase.waiter.domain.events.MealPayedEvent;
import restaurant.showcase.waiter.domain.events.order.OrderDomainEvent;

import java.util.HashMap;

@Log4j2
@AllArgsConstructor
public class KafkaAdapter {

    private final KafkaTemplate<String, HashMap<String, Object>> kafkaTemplate;

    @EventListener
    public void onMealPayed(MealPayedEvent mealPayedEvent) {
        KafkaTopic kafkaTopic = KafkaTopic.fromClassName(mealPayedEvent.getClass());
        KafkaMessage kafkaMessage = new KafkaMessage(mealPayedEvent);
        this.send(kafkaTopic, kafkaMessage);
    }

    @EventListener
    public void onDomainEvent(OrderDomainEvent domainEvent) {
        KafkaTopic kafkaTopic = KafkaTopic.fromClassName(domainEvent.getClass());
        KafkaMessage kafkaMessage = new KafkaMessage(domainEvent);
        this.send(kafkaTopic, kafkaMessage);
    }

    private void send(KafkaTopic kafkaTopic, KafkaMessage kafkaMessage) {
        kafkaTemplate.send(kafkaTopic.getTopicName(), kafkaMessage.getValues());
        log.info("[{}] {} sent to kafka", kafkaMessage.getEventReferenceKey(), kafkaMessage.getEventName());
    }

}
