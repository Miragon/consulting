package restaurant.showcase.waiter.application.port.in.prepareMeal;

import lombok.Getter;
import restaurant.showcase.waiter.domain.DiningOption;
import restaurant.showcase.waiter.domain.Food;

@Getter
public class PrepareMealInCommand {
    private final String orderId;
    private final String customerName;
    private final Food food;
    private final DiningOption diningOption;

    public PrepareMealInCommand(String orderId, String customerName, String food, String diningOption) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.food = Food.fromString(food);
        this.diningOption = DiningOption.fromString(diningOption);
    }
}
