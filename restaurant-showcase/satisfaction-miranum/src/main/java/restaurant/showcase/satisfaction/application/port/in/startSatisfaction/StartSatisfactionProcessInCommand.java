package restaurant.showcase.satisfaction.application.port.in.startSatisfaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StartSatisfactionProcessInCommand {
    private final String orderId;
    private final String customerName;
    private final String meal;
}
