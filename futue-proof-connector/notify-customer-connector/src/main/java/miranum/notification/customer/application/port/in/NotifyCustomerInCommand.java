package miranum.notification.customer.application.port.in;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplateProperty;
import io.miragon.miranum.connect.elementtemplate.api.PropertyType;
import lombok.Getter;

@Getter
public class NotifyCustomerInCommand {
    @ElementTemplateProperty(type = PropertyType.STRING, label = "Notification Method", notEmpty = true)
    private final String notificationMethod;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Topic", notEmpty = true)
    private final String topic;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Message", notEmpty = true)
    private final String message;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Customer Name", notEmpty = true)
    private final String customerName;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Customer No.", notEmpty = true)
    private final String customerNumber;

    public NotifyCustomerInCommand(String notificationMethod, String topic, String message, String customerName, String customerNumber) {
        this.notificationMethod = notificationMethod;
        this.topic = topic;
        this.message = message;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
    }
}
