package pizza.sync.service;

import org.springframework.stereotype.Component;

@Component
public class VinoOrderService implements OrderService {
    private final static long ONE_SECOND = 1000L;

    public void execute(OrderRO orderRequest) throws InterruptedException {
        System.out.print("We're working on getting your Vino! ");
        for (int i = 0; i < 3; i++) {
            System.out.print("..");
            Thread.sleep(ONE_SECOND);
        }
        System.out.printf("%nVino for %s is up!%n", orderRequest.getCustomerName());
    }
}

