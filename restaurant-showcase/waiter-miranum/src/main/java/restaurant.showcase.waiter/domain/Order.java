package restaurant.showcase.waiter.domain;

import lombok.Getter;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealInCommand;

import java.time.LocalDateTime;

@Getter
public class Order {
    private final String orderId;
    private final String customerName;
    private final Food food;
    private final DiningOption diningOption;

    public Order(PlaceOrderInCommand placeOrderInCommand){
        this.orderId = String.format("order-%s-%s", placeOrderInCommand.getCustomerName(), LocalDateTime.now());
        this.customerName = placeOrderInCommand.getCustomerName();
        this.food = Food.fromString(placeOrderInCommand.getMeal());
        this.diningOption = DiningOption.fromString(placeOrderInCommand.getDiningOption());
    }

    public Order(PrepareMealInCommand prepareMealInCommand) {
        this.orderId = prepareMealInCommand.getOrderId();
        this.customerName = prepareMealInCommand.getCustomerName();
        this.food = Food.fromString(prepareMealInCommand.getMeal());
        this.diningOption = DiningOption.fromString(prepareMealInCommand.getDiningOption());
    }
}
