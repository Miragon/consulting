package restaurant.showcase.waiter.websocket;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
@AllArgsConstructor
public class WebsocketNotificationListener {

    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    }

    public void notify(String message) {
        messagingTemplate.convertAndSend("/topic/message", message);
    }

}
