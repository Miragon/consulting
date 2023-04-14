package restaurant.showcase.waiter.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.adapter.out.websocket.config.WebsocketNotificationListener;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealInCommand;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealUseCase;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealOutCommand;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealPort;
import restaurant.showcase.waiter.domain.Order;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrepareMealService implements PrepareMealUseCase {

    private final PrepareMealPort prepareMealPort;
    private final WebsocketNotificationListener websocketNotificationListener;

    @Override
    public void prepareMeal(PrepareMealInCommand prepareMealInCommand) {
        var order = new Order(prepareMealInCommand);
        log.info("[{}] Telling Kitchen to prepare {}.",
                order.getOrderId(),
                order.getFood().getName());
        String response = prepareMealPort.prepareMeal(map(order));
        websocketNotificationListener.notify(order.getOrderId(), response);
    }

    private PrepareMealOutCommand map(Order order) {
        return new PrepareMealOutCommand(
                order.getOrderId(),
                order.getCustomerName(),
                order.getFood().getName(),
                order.getDiningOption().getName()
        );
    }
}
