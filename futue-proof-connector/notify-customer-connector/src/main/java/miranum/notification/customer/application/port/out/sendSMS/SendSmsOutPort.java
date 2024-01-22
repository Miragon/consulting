package miranum.notification.customer.application.port.out.sendSMS;

public interface SendSmsOutPort {
    void notifyCustomer(SendSmsOutCommand command);
}
