package restaurant.showcase.waiter.application.port.out.notifyCustomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotifyCustomerOutCommand {
    private String orderId;
    private String message;
}
