package miranum.notification.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import miranum.notification.customer.application.port.in.NotifyCustomerInCommand;

@AllArgsConstructor
@Getter
public class Notification {
    private final NotificationMethod notificationMethod;
    private final String topic;
    private final String message;
    private final Customer customer;

    public Notification(NotifyCustomerInCommand notifyCustomerInCommand) {
        this.topic = notifyCustomerInCommand.getMailTopic();
        this.message = notifyCustomerInCommand.getMessage();
        this.customer = new Customer(notifyCustomerInCommand.getCustomerName(), notifyCustomerInCommand.getCustomerMobilePhone(), notifyCustomerInCommand.getCustomerEMail());
        this.notificationMethod = NotificationMethod.fromString(notifyCustomerInCommand.getNotificationMethod());
    }
}
