package restaurant.showcase.satisfaction.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.showcase.satisfaction.application.port.in.startSatisfaction.StartSatisfactionProcessInCommand;
import restaurant.showcase.satisfaction.application.port.in.startSatisfaction.StartSatisfactionProcessUseCase;
import restaurant.showcase.satisfaction.application.port.out.startSatisfaction.StartSatisfactionProcessOutCommand;
import restaurant.showcase.satisfaction.application.port.out.startSatisfaction.StartSatisfactionProcessOutPort;

@Service
@RequiredArgsConstructor
public class StartSatisfactionProcessService implements StartSatisfactionProcessUseCase {

    private final StartSatisfactionProcessOutPort startSatisfactionProcessOutPort;

    @Override
    public void onOrderHandled(StartSatisfactionProcessInCommand startSatisfactionProcessInCommand) {
        startSatisfactionProcessOutPort.onOrderHandled(new StartSatisfactionProcessOutCommand(
                startSatisfactionProcessInCommand.getOrderId(),
                startSatisfactionProcessInCommand.getCustomerName(),
                startSatisfactionProcessInCommand.getMeal()
        ));
    }
}
