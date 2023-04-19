package restaurant.showcase.waiter.adapter.out.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("kafka")
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderPlacedTopic(){
        return TopicBuilder.name(KafkaTopic.ORDER_PLACED.getTopicName()).build();
    }

    @Bean
    public NewTopic mealPreparedTopic(){
        return TopicBuilder.name(KafkaTopic.MEAL_PREPARED.getTopicName()).build();
    }

    @Bean
    public NewTopic mealPayedTopic(){
        return TopicBuilder.name(KafkaTopic.MEAL_PAYED.getTopicName()).build();
    }

    @Bean
    public NewTopic mealServedTopic(){
        return TopicBuilder.name(KafkaTopic.MEAL_SERVED.getTopicName()).build();
    }

    @Bean
    public NewTopic customerReassuredTopic(){
        return TopicBuilder.name(KafkaTopic.CUSTOMER_REASSURED.getTopicName()).build();
    }

    @Bean
    public NewTopic checkIssuedTopic(){
        return TopicBuilder.name(KafkaTopic.CHECK_ISSUED.getTopicName()).build();
    }

    @Bean
    public NewTopic checkHandedTopic(){
        return TopicBuilder.name(KafkaTopic.CHECK_HANDED.getTopicName()).build();
    }
}
