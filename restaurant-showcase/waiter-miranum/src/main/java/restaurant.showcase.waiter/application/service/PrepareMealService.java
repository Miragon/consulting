package restaurant.showcase.waiter.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.adapter.out.websocket.config.WebsocketNotificationListener;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealInCommand;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealUseCase;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealOutCommand;
import restaurant.showcase.waiter.application.port.out.prepareMeal.PrepareMealPort;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrepareMealService implements PrepareMealUseCase {

    private final PrepareMealPort prepareMealPort;
    private final WebsocketNotificationListener websocketNotificationListener;

    @Override
    public void prepareMeal(PrepareMealInCommand prepareMealInCommand) {
        log.info("[{}] Telling Kitchen to prepare {}.",
                prepareMealInCommand.getOrderId(),
                prepareMealInCommand.getFood().getName());
        String response = prepareMealPort.prepareMeal(map(prepareMealInCommand));
        websocketNotificationListener.notify(prepareMealInCommand.getOrderId(), response);
    }

    private PrepareMealOutCommand map(PrepareMealInCommand prepareMealInCommand) {
        return new PrepareMealOutCommand(
                prepareMealInCommand.getOrderId(),
                prepareMealInCommand.getCustomerName(),
                prepareMealInCommand.getFood().getName(),
                prepareMealInCommand.getDiningOption().getName()
        );
    }
}
