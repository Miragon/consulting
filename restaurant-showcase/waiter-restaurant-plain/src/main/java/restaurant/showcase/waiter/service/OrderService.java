package restaurant.showcase.waiter.service;

public interface OrderService {
    void execute(OrderRO orderRequest) throws InterruptedException;
}
