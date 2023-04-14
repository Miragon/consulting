package restaurant.showcase.waiter.adapter.out.websocket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class WebsocketNotificator {

    private SimpMessageSendingOperations messagingTemplate;

    public void notify(String orderId, String message) {
        log.info("[{}] Notifying Customer: {}", orderId, message);
        messagingTemplate.convertAndSend(String.format("/topic/message/%s", orderId), message);
    }

}
