package restaurant.showcase.proxy.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledInCommand;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledUseCase;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final OrderHandledUseCase orderHandledUseCase;
    @KafkaListener(topics = "check-handed")
    public void consumeCheckHandled(HashMap<String, Object> eventValues){
        orderHandledUseCase.onOrderHandled(new OrderHandledInCommand(eventValues));
    }
}
