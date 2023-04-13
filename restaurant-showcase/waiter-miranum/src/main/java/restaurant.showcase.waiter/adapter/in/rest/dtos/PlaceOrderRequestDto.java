package restaurant.showcase.waiter.adapter.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceOrderRequestDto {
    private String customerName;
    private String meal;
    private String diningOption;
}
