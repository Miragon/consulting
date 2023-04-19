package restaurant.showcase.waiter.application.port.out.placeOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import restaurant.showcase.waiter.domain.Order;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceOrderOutCommand {

    private Order order;
}
