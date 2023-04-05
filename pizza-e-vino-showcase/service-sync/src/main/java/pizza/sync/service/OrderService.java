package pizza.sync.service;

public interface OrderService {
    void execute(OrderRO orderRequest) throws InterruptedException;
}
