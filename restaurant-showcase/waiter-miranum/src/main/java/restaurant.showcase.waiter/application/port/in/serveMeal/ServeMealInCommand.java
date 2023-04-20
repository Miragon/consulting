package restaurant.showcase.waiter.application.port.in.serveMeal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServeMealInCommand {
    private String orderId;
    private String customerName;
    private String meal;
    private String diningOption;
}
