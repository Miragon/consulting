package restaurant.showcase.waiter.application.port.out.payMeal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PayMealOutCommand {

    private String processKey;
    private Map<String, Object> variables;
}
