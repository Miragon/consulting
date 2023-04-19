package restaurant.showcase.waiter.adapter.out.message;

import io.miragon.miranum.connect.message.api.CorrelateMessageCommand;
import io.miragon.miranum.connect.message.api.MessageApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.out.payMeal.PayMealOutCommand;
import restaurant.showcase.waiter.application.port.out.payMeal.PayMealOutPort;

@AllArgsConstructor
@Component
public class MessageAdapter implements PayMealOutPort {

    private final MessageApi messageApi;
    private static final String MESSAGE_KEY = "ReadyToPay";

    @Override
    public void payMeal(PayMealOutCommand placeOrderOutCommand) {
        messageApi.correlateMessage(new CorrelateMessageCommand(MESSAGE_KEY, placeOrderOutCommand.getOrderId()));
    }
}
