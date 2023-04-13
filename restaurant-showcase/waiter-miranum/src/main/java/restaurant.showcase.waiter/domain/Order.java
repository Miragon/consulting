package restaurant.showcase.waiter.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Order {
    private final String orderId;
    private final String customerName;
    private final Food food;
    private final DiningOption diningOption;
}
