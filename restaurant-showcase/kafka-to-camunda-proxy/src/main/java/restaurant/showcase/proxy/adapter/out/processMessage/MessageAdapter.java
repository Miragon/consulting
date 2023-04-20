package restaurant.showcase.proxy.adapter.out.processMessage;

import io.miragon.miranum.connect.message.api.CorrelateMessageCommand;
import io.miragon.miranum.connect.message.api.MessageApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutCommand;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutPort;

import java.util.Collections;

@Slf4j
@Component
@AllArgsConstructor
public class MessageAdapter implements OrderHandledOutPort {

    private final MessageApi messageApi;
    private static final String START_SATISFACTION_SURVEY_MESSAGE_REFERENCE = "StartSatisfactionSurvey";

    @Override
    public void onOrderHandled(OrderHandledOutCommand placeOrderOutCommand) {
        messageApi.correlateMessage(new CorrelateMessageCommand(
                START_SATISFACTION_SURVEY_MESSAGE_REFERENCE,
                placeOrderOutCommand.getCorrelationKey(),
                Collections.emptyMap()));
        log.info("[{}] Sent '{}'-Message to Process Engine.",
                placeOrderOutCommand.getCorrelationKey(),
                START_SATISFACTION_SURVEY_MESSAGE_REFERENCE);
    }
}
