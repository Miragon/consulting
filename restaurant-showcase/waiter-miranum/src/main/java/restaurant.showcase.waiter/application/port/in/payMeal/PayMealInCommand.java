package restaurant.showcase.waiter.application.port.in.payMeal;

import lombok.Getter;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PayMealRequestDto;

@Getter
public class PayMealInCommand {

    private final String orderId;

    public PayMealInCommand(PayMealRequestDto payMealRequestDto) {
        this.orderId = payMealRequestDto.getOrderId();
    }
}