package miranum.notification.customer.application.port.in;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplateProperty;
import io.miragon.miranum.connect.elementtemplate.api.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotifyCustomerInCommand {
    @ElementTemplateProperty(type = PropertyType.STRING, label = "Notification Method", notEmpty = true)
    private String notificationMethod;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Topic", notEmpty = true)
    private String mailTopic;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Message", notEmpty = true)
    private String message;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Customer Name", notEmpty = true)
    private String customerName;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Mobile Phone Number", notEmpty = true)
    private String customerMobilePhone;

    @ElementTemplateProperty(type = PropertyType.STRING, label = "Customer Email", notEmpty = true)
    private String customerEMail;
}
