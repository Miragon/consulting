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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotifyCustomerService implements NotifyCustomerUseCase {

    private SendSmsOutPort sendSmsOutPort;
    private SendMailOutPort sendMailOutPort;

    @Override
    public void notifyCustomer(NotifyCustomerInCommand notifyCustomerInCommand) throws Exception {
        Customer customer = new Customer(notifyCustomerInCommand.getCustomerName(), notifyCustomerInCommand.getCustomerMobilePhone(), notifyCustomerInCommand.getCustomerEMail());
        Notification notification = new Notification(notifyCustomerInCommand.getNotificationMethod(), notifyCustomerInCommand.getMailTopic(), notifyCustomerInCommand.getMessage(), customer);

        if (notification.getNotificationMethod().equals("SMS")) {
            sendSmsOutPort.notifyCustomer(new SendSmsOutCommand(customer.getName(), customer.getMobilephone(), notification.getTopic(), notification.getMessage()));
        } else if (notification.getNotificationMethod().equals("EMAIL")) {
            sendMailOutPort.notifyCustomer(new SendMailOutCommand(customer.getName(), customer.getEmail(), notification.getTopic(), notification.getMessage()));
        } else {
            throw new Exception("Invalid notification method");
        }
    }
}
