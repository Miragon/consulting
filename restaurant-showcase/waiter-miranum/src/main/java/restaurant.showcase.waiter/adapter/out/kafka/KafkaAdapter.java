package restaurant.showcase.waiter.adapter.out.kafka;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.domain.events.*;


@Component
@Profile("kafka")
@AllArgsConstructor
public class KafkaAdapter {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @EventListener
    public void onHandedOrder(HandedCheckEvent handedCheckEvent) {
        kafkaTemplate.send("handed-check", handedCheckEvent);
    }

    @EventListener
    public void onMealPayed(MealPayedEvent mealPayedEvent){
        kafkaTemplate.send("meal-payed", mealPayedEvent);
    }

    @EventListener
    public void onCheckIssued(CheckIssuedEvent checkIssuedEvent){
        kafkaTemplate.send("check-issued", checkIssuedEvent);
    }

    @EventListener
    public void onCustomerReassured(CustomerReassuredEvent customerReassuredEvent){
        kafkaTemplate.send("customer-reassured", customerReassuredEvent);
    }

    @EventListener
    public void onMealPrepared(MealPreparedEvent mealPreparedEvent){
        kafkaTemplate.send("meal-prepared", mealPreparedEvent);
    }
}
