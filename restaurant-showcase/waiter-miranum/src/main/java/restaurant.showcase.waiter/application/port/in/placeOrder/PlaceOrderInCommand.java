package restaurant.showcase.waiter.application.port.in.placeOrder;

import lombok.Getter;
import restaurant.showcase.waiter.domain.DiningOption;
import restaurant.showcase.waiter.domain.Food;

@Getter
public class PlaceOrderInCommand {
    private final String customerName;
    private final Food food;
    private final DiningOption diningOption;

    public PlaceOrderInCommand(String customerName, String food, String diningOption) {
        this.customerName = customerName;
        this.food = Food.fromString(food);
        this.diningOption = DiningOption.fromString(diningOption);
    }
}
