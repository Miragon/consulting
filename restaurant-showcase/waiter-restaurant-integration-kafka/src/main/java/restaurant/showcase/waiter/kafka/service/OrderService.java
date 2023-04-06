package restaurant.showcase.waiter.kafka.service;

public interface OrderService {
    void execute(String customerName, String orderMessage) throws Exception;
}
