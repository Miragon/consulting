package restaurant.showcase.waiter.adapter.out.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import restaurant.showcase.waiter.domain.events.OrderDomainEvent;

import java.util.HashMap;

@Log4j2
@AllArgsConstructor
public class KafkaAdapter {

    private final KafkaTemplate<String, HashMap<String, Object>> kafkaTemplate;

    @EventListener
    public void onDomainEvent(OrderDomainEvent domainEvent) {
        KafkaTopic kafkaTopic = KafkaTopic.fromClassName(domainEvent.getClass());
        kafkaTemplate.send(kafkaTopic.getTopicName(), domainEvent.convertValuesToMap());
        log.info("{} sent to kafka", domainEvent.getClass().getSimpleName());
    }
}
