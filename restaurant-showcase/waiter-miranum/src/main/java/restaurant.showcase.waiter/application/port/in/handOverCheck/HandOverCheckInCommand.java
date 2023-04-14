package restaurant.showcase.waiter.application.port.in.handOverCheck;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HandOverCheckInCommand {
    private String orderId;
    private String customerName;
    private String meal;
    private String diningOption;
}
