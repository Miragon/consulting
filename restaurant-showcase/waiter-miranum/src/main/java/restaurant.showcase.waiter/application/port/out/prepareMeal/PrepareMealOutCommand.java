package restaurant.showcase.waiter.application.port.out.prepareMeal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PrepareMealOutCommand {
    private final String orderId;
    private final String customerName;
    private final String meal;
    private final String diningOption;
}
