package restaurant.showcase.waiter.adapter.out.websocket;

import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WebsocketNotificationListener {

    private SimpMessageSendingOperations messagingTemplate;

    public void notify(String orderId, String message) {
        messagingTemplate.convertAndSend(String.format("/topic/message/%s", orderId), message);
    }

}
