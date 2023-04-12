package restaurant.showcase.waiter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseRO {
    private String orderId;
    private String message;
}
