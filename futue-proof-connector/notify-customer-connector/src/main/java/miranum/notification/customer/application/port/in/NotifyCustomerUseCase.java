package miranum.notification.customer.application.port.in;

public interface NotifyCustomerUseCase {
    void notifyCustomer(NotifyCustomerInCommand notifyCustomerInCommand) throws Exception;
}
