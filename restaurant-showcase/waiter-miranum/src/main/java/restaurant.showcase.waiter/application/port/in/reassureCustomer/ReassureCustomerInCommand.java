package restaurant.showcase.waiter.application.port.in.reassureCustomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReassureCustomerInCommand {
    private String orderId;
    private String customerName;
    private String meal;
    private String diningOption;
}
