package miranum.notification.customer.application.port.out.sendMail;

public interface SendMailOutPort {
    void notifyCustomer(SendMailOutCommand command);

}