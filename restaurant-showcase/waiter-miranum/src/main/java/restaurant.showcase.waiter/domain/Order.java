package restaurant.showcase.waiter.domain;

import lombok.Getter;
import restaurant.showcase.waiter.application.port.in.handOverCheck.HandOverCheckInCommand;
import restaurant.showcase.waiter.application.port.in.issueCheck.IssueCheckInCommand;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealInCommand;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealInCommand;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerInCommand;
import restaurant.showcase.waiter.application.port.in.serveMeal.ServeMealInCommand;

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

    public Order(IssueCheckInCommand issueCheckInCommand) {
        this.orderId = issueCheckInCommand.getOrderId();
        this.customerName = issueCheckInCommand.getCustomerName();
        this.food = Food.fromString(issueCheckInCommand.getMeal());
        this.diningOption = DiningOption.fromString(issueCheckInCommand.getDiningOption());
    }

    public Order(ReassureCustomerInCommand reassureCustomerInCommand) {
        this.orderId = reassureCustomerInCommand.getOrderId();
        this.customerName = reassureCustomerInCommand.getCustomerName();
        this.food = Food.fromString(reassureCustomerInCommand.getMeal());
        this.diningOption = DiningOption.fromString(reassureCustomerInCommand.getDiningOption());
    }

    public Order(ServeMealInCommand serveMealInCommand) {
        this.orderId = serveMealInCommand.getOrderId();
        this.customerName = serveMealInCommand.getCustomerName();
        this.food = Food.fromString(serveMealInCommand.getMeal());
        this.diningOption = DiningOption.fromString(serveMealInCommand.getDiningOption());
    }

    public Order(HandOverCheckInCommand handOverCheckInCommand) {
        this.orderId = handOverCheckInCommand.getOrderId();
        this.customerName = handOverCheckInCommand.getCustomerName();
        this.food = Food.fromString(handOverCheckInCommand.getMeal());
        this.diningOption = DiningOption.fromString(handOverCheckInCommand.getDiningOption());
    }
}
