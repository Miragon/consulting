package restaurant.showcase.satisfaction.adapter.out.processMessage;

import io.miragon.miranum.connect.message.api.CorrelateMessageCommand;
import io.miragon.miranum.connect.message.api.MessageApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import restaurant.showcase.satisfaction.application.port.out.startSatisfaction.StartSatisfactionProcessOutCommand;
import restaurant.showcase.satisfaction.application.port.out.startSatisfaction.StartSatisfactionProcessOutPort;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class MessageAdapter implements StartSatisfactionProcessOutPort {

    private final MessageApi messageApi;
    private static final String START_SATISFACTION_SURVEY_MESSAGE_REFERENCE = "StartSatisfactionSurvey";

    @Override
    public void onOrderHandled(StartSatisfactionProcessOutCommand placeOrderOutCommand) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("orderId", placeOrderOutCommand.getCorrelationKey());
        variables.put("customerName", placeOrderOutCommand.getCustomerName());
        variables.put("meal", placeOrderOutCommand.getMeal());

        messageApi.correlateMessage(new CorrelateMessageCommand(
                START_SATISFACTION_SURVEY_MESSAGE_REFERENCE,
                placeOrderOutCommand.getCorrelationKey(),
                variables));
        log.info("[{}] Sent '{}'-Message to Process Engine.",
                placeOrderOutCommand.getCorrelationKey(),
                START_SATISFACTION_SURVEY_MESSAGE_REFERENCE);
    }
}
