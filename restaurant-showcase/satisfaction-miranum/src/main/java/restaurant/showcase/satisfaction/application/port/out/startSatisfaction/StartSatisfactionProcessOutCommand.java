package restaurant.showcase.satisfaction.application.port.out.startSatisfaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class StartSatisfactionProcessOutCommand {
    private final String correlationKey;
    private final String customerName;
    private final String meal;
}
