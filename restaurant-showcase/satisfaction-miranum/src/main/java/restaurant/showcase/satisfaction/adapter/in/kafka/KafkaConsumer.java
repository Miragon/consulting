package restaurant.showcase.satisfaction.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import restaurant.showcase.satisfaction.application.port.in.startSatisfaction.StartSatisfactionProcessInCommand;
import restaurant.showcase.satisfaction.application.port.in.startSatisfaction.StartSatisfactionProcessUseCase;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final StartSatisfactionProcessUseCase startSatisfactionProcessUseCase;

    @Value("${kafka.in.restaurant.process.start}")
    private String processStartEvent;

    @KafkaListener(topics = "#{'${kafka.in.restaurant.topics}'.replaceAll('\\s+', '').split(',')}")
    public void consumeEvents(HashMap<String, Object> eventValues){
        String eventName = eventValues.get("eventName").toString();
        String eventReferenceKey = eventValues.get("eventReferenceKey").toString();
        log.info("[{}] {} event received", eventReferenceKey, eventName);

        if (processStartEvent.equals(eventName)) {
            startSatisfactionProcessUseCase.onOrderHandled(new StartSatisfactionProcessInCommand(
                    eventValues.get("orderId").toString(),
                    eventValues.get("customerName").toString(),
                    eventValues.get("meal").toString())
            );
        }
    }
}
