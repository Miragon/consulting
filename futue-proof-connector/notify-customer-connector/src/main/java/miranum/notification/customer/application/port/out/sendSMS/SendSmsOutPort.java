package miranum.notification.customer.application.port.out.sendSMS;

import miranum.notification.customer.application.port.out.sendMail.SendMailOutCommand;

public interface SendSmsOutPort {
    void notifyCustomer(SendSmsOutCommand command);
}
