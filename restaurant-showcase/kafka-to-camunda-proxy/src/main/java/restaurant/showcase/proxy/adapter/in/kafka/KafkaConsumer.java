package restaurant.showcase.proxy.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledInCommand;
import restaurant.showcase.proxy.application.port.in.orderHandled.OrderHandledUseCase;

import java.util.HashMap;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final OrderHandledUseCase orderHandledUseCase;
    private static final String CHECK_HANDLED_EVENT = "CheckHandedEvent";

    @KafkaListener(topics = "#{'${kafka.in.restaurant.topics}'.replaceAll('\\s+', '').split(',')}")
    public void consumeEvent(HashMap<String, Object> eventValues){
        String eventName = eventValues.get("eventName").toString();
        String eventReferenceKey = eventValues.get("eventReferenceKey").toString();
        log.info("[{}] {} event received", eventReferenceKey, eventName);

        if (CHECK_HANDLED_EVENT.equals(eventName)) {
            orderHandledUseCase.onOrderHandled(new OrderHandledInCommand(eventValues.get("orderId").toString()));
        }
    }
}
