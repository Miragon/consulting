package restaurant.showcase.waiter.application.service;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealInCommand;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealUseCase;
import restaurant.showcase.waiter.application.port.out.payMeal.PayMealOutCommand;
import restaurant.showcase.waiter.application.port.out.payMeal.PayMealOutPort;
import restaurant.showcase.waiter.domain.events.MealPayedEvent;

@AllArgsConstructor
@Service
public class PayMealService implements PayMealUseCase {
    private final PayMealOutPort payMealOutPort;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void payMeal(PayMealInCommand payMealInCommand) {
        this.payMealOutPort.payMeal(new PayMealOutCommand(payMealInCommand.getOrderId()));
        applicationEventPublisher.publishEvent(new MealPayedEvent(payMealInCommand.getOrderId()));
    }
}
