package restaurant.showcase.waiter.adapter.out.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.domain.events.DomainEvent;

import java.util.HashMap;

@Log4j2
@Component
@Profile("kafka")
@AllArgsConstructor
public class KafkaAdapter {

    private final KafkaTemplate<String, HashMap<String, Object>> kafkaTemplate;

    @EventListener
    public void onDomainEvent(DomainEvent domainEvent) {
        KafkaTopic kafkaTopic = KafkaTopic.fromClassName(domainEvent.getClass());
        kafkaTemplate.send(kafkaTopic.getTopicName(), domainEvent.convertToMap());
        log.info("{} sent to kafka", domainEvent.getClass().getSimpleName());
    }
}
