package restaurant.showcase.waiter.application.port.out.prepareMeal;

import lombok.RequiredArgsConstructor;
import restaurant.showcase.waiter.domain.Order;

@RequiredArgsConstructor
public class PrepareMealOutCommand {

    private final String orderId;
    private final String customerName;
    private final String meal;
    private final String diningOption;

    public PrepareMealOutCommand(Order order) {
        this.orderId = order.getOrderId();
        this.customerName = order.getCustomerName();
        this.meal = order.getFood().getName();
        this.diningOption = order.getDiningOption().getName();
    }
}