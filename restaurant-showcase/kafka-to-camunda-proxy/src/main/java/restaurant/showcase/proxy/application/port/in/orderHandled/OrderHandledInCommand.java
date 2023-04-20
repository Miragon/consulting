package restaurant.showcase.proxy.application.port.in.orderHandled;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
@Getter
public class OrderHandledInCommand {
    private final String orderId;
}
