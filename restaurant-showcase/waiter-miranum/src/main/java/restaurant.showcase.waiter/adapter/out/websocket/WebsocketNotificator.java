package restaurant.showcase.waiter.adapter.out.websocket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.out.notifyCustomer.NotifyCustomerOutCommand;
import restaurant.showcase.waiter.application.port.out.notifyCustomer.NotifyCustomerOutPort;

@Slf4j
@Component
@AllArgsConstructor
public class WebsocketNotificator implements NotifyCustomerOutPort {

    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void notifyCustomer(NotifyCustomerOutCommand notifyCustomerOutCommand) {
        log.info("[{}] Notifying Customer: {}", notifyCustomerOutCommand.getOrderId(), notifyCustomerOutCommand.getMessage());
        messagingTemplate.convertAndSend(String.format("/topic/message/%s", notifyCustomerOutCommand.getOrderId()), notifyCustomerOutCommand.getMessage());
    }
}
