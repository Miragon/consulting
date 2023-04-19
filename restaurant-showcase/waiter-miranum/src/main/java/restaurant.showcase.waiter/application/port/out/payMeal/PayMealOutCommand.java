package restaurant.showcase.waiter.application.port.out.payMeal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class PayMealOutCommand {
    private final String orderId;

}
