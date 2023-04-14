package restaurant.showcase.waiter.application.port.out.notifyCustomer;

public interface PlaceOrderPort {
    void notifyCustomer(NotifyCustomerOutCommand notifyCustomerOutCommand);
}
