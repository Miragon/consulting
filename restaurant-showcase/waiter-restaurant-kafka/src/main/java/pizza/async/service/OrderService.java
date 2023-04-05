package pizza.async.service;

public interface OrderService {
    void execute(String customerName, String orderMessage) throws Exception;
}
