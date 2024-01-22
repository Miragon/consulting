package miranum.notification.customer.application.service;

import lombok.AllArgsConstructor;
import miranum.notification.customer.application.port.in.NotifyCustomerInCommand;
import miranum.notification.customer.application.port.in.NotifyCustomerUseCase;
import miranum.notification.customer.application.port.out.sendMail.SendMailOutCommand;
import miranum.notification.customer.application.port.out.sendMail.SendMailOutPort;
import miranum.notification.customer.application.port.out.sendSMS.SendSmsOutCommand;
import miranum.notification.customer.application.port.out.sendSMS.SendSmsOutPort;
import miranum.notification.customer.domain.Customer;
import miranum.notification.customer.domain.Notification;
import miranum.notification.customer.domain.NotificationMethod;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotifyCustomerService implements NotifyCustomerUseCase {

    private SendSmsOutPort sendSmsOutPort;
    private SendMailOutPort sendMailOutPort;

    @Override
    public void notifyCustomer(NotifyCustomerInCommand notifyCustomerInCommand) throws Exception {
        Customer customer = new Customer(notifyCustomerInCommand);
        Notification notification = new Notification(notifyCustomerInCommand);

        if (notification.getNotificationMethod() == NotificationMethod.SMS) {
            sendSmsOutPort.notifyCustomer(new SendSmsOutCommand(customer.getName(), customer.getMobilephone(), notification.getTopic(), notification.getMessage()));
        } else if (notification.getNotificationMethod() == NotificationMethod.EMAIL) {
            sendMailOutPort.notifyCustomer(new SendMailOutCommand(customer.getName(), customer.getEmail(), notification.getTopic(), notification.getMessage()));
        } else {
            throw new Exception("Invalid notification method");
        }
    }
}
