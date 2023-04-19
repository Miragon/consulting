package restaurant.showcase.waiter.adapter.out.kafka;

import com.google.protobuf.Any;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.domain.events.HandedCheckEvent;

import java.util.HashMap;

@Component
@Profile("kafka")
public class KafkaAdapter {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @EventListener
    public void onHandedOrder(HandedCheckEvent handedCheckEvent) {
        kafkaTemplate.send("handed-check", handedCheckEvent);
    }
}
