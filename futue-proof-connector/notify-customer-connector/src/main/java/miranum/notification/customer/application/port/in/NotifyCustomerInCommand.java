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

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Mobile Phone Number", notEmpty = true)
    private final String customerMobilePhone;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Customer Email", notEmpty = true)
    private final String customerEMail;


    public NotifyCustomerInCommand(String notificationMethod, String topic, String message, String customerName, String customerMobilePhone, String customerEMail){
        this.notificationMethod = notificationMethod;
        this.topic = topic;
        this.message = message;
        this.customerName = customerName;
        this.customerMobilePhone = customerMobilePhone;
        this.customerEMail = customerEMail;
    }
}
