package restaurant.showcase.waiter.application.port.in.placeOrder;

import lombok.Getter;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderRequestDto;

@Getter
public class PlaceOrderInCommand {
    private final String customerName;
    private final String meal;
    private final String diningOption;

    public PlaceOrderInCommand(PlaceOrderRequestDto placeOrderInCommand) {
        this.customerName = placeOrderInCommand.getCustomerName();
        this.meal = placeOrderInCommand.getMeal();
        this.diningOption = placeOrderInCommand.getDiningOption();
    }
}
