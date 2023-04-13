package restaurant.showcase.waiter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRO {
    private String orderId;
    private String customerName;
    private String meal;
    private String diningOption;
}

