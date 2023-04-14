package restaurant.showcase.waiter.application.port.in.placeOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import restaurant.showcase.waiter.domain.DiningOption;
import restaurant.showcase.waiter.domain.Food;

@Getter
@AllArgsConstructor
public class PlaceOrderInCommand {
    private final String customerName;
    private final String meal;
    private final String diningOption;
}
