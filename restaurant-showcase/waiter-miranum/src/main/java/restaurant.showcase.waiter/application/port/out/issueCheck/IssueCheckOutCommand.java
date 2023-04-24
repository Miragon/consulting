package restaurant.showcase.waiter.application.port.out.issueCheck;

import lombok.RequiredArgsConstructor;
import restaurant.showcase.waiter.domain.Order;

@RequiredArgsConstructor
public class IssueCheckOutCommand {

    private final String orderId;
    private final String customerName;
    private final String meal;
    private final String diningOption;

    public IssueCheckOutCommand(Order order) {
        this.orderId = order.getOrderId();
        this.customerName = order.getCustomerName();
        this.meal = order.getFood().getName();
        this.diningOption = order.getDiningOption().getName();
    }
}