package restaurant.showcase.proxy.adapter.out.processMessage;

import io.miragon.miranum.connect.message.api.CorrelateMessageCommand;
import io.miragon.miranum.connect.message.api.MessageApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutCommand;
import restaurant.showcase.proxy.application.port.out.orderHandled.OrderHandledOutPort;

@AllArgsConstructor
@Component
public class MessageAdapter implements OrderHandledOutPort {

    private final MessageApi messageApi;
    private static final String PAYMENT_HANDLED_MESSAGE_REFERENCE = "orderPaymentHandled";

    @Override
    public void onOrderHandled(OrderHandledOutCommand placeOrderOutCommand) {
        messageApi.correlateMessage(new CorrelateMessageCommand(
                PAYMENT_HANDLED_MESSAGE_REFERENCE,
                placeOrderOutCommand.getCorrelationKey(),
                placeOrderOutCommand.getVariables()));
    }
}
