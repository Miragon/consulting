package miranum.notification.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import miranum.notification.customer.application.port.in.NotifyCustomerInCommand;

@AllArgsConstructor
@Getter
public class Customer {
    private final String name;
    private final String email;
    private final String mobilephone;

    public Customer(NotifyCustomerInCommand notifyCustomerInCommand) {
        this.name = notifyCustomerInCommand.getCustomerName();
        this.email = notifyCustomerInCommand.getCustomerEMail();
        this.mobilephone = notifyCustomerInCommand.getCustomerMobilePhone();
    }
}
