package restaurant.showcase.waiter.application.port.out.prepareMeal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrepareMealOutCommand {
    private final String orderId;
    private final String customerName;
    private final String meal;
    private final String diningOption;
}
