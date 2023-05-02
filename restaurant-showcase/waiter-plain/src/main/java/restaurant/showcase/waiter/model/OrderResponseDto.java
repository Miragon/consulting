package restaurant.showcase.waiter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderResponseDto {
    private String orderId;
    private String message;
}
