package restaurant.showcase.waiter.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealInCommand;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealUseCase;
import restaurant.showcase.waiter.application.port.out.payMeal.PayMealOutCommand;
import restaurant.showcase.waiter.application.port.out.payMeal.PayMealOutPort;

@AllArgsConstructor
@Service
public class PayMealService implements PayMealUseCase {
    private final PayMealOutPort payMealOutPort;

    @Override
    public void payMeal(PayMealInCommand payMealInCommand) {
        this.payMealOutPort.payMeal(new PayMealOutCommand(payMealInCommand.getOrderId()));
    }
}
