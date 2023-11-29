package applicant.employee.application.adapter.out.websocket;

import applicant.employee.application.application.port.out.formFeedback.FormFeedbackOutCommand;
import applicant.employee.application.application.port.out.formFeedback.FormFeedbackOutPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class WebsocketNotificator implements FormFeedbackOutPort {

    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void propagateFeedback(FormFeedbackOutCommand formFeedbackOutCommand) {
        log.info("[{}] Notifying: {}", formFeedbackOutCommand.getApplicationId(), formFeedbackOutCommand.getMessage());
        messagingTemplate.convertAndSend(String.format("/topic/message/%s", formFeedbackOutCommand.getApplicationId()), formFeedbackOutCommand.getMessage());
    }
}
