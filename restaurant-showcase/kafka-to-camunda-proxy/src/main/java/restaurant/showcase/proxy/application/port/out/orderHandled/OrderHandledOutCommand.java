package restaurant.showcase.proxy.application.port.out.orderHandled;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;


@Getter
@RequiredArgsConstructor
public class OrderHandledOutCommand {
    private final String correlationKey;
    private final HashMap<String, Object> variables;
}
