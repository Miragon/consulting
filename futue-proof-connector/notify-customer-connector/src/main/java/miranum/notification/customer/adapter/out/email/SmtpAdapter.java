package miranum.notification.customer.adapter.out.email;

import miranum.notification.customer.adapter.out.sms.TwilioSmsAdapter;
import miranum.notification.customer.application.port.out.sendMail.SendMailOutCommand;
import miranum.notification.customer.application.port.out.sendMail.SendMailOutPort;

public class SmtpAdapter implements SendMailOutPort {
    @Override
    public void notifyCustomer(SendMailOutCommand command) {
        System.out.println("SmtpAdapter: notifyCustomer");
    }
}
