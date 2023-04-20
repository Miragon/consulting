package restaurant.showcase.waiter.adapter.out.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;

@Configuration
@ConditionalOnProperty(name = "application.kafka.enabled", havingValue = "true")
public class KafkaAdapterConfig {

    @Bean
    public KafkaAdapter kafkaAdapter(KafkaTemplate<String, HashMap<String, Object>> kafkaTemplate) {
        return new KafkaAdapter(kafkaTemplate);
    }
}
