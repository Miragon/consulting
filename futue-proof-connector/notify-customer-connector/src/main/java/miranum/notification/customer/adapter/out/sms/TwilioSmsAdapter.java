package miranum.notification.customer.adapter.out.sms;

import miranum.notification.customer.application.port.out.sendSMS.SendSmsOutCommand;
import miranum.notification.customer.application.port.out.sendSMS.SendSmsOutPort;

public class TwilioSmsAdapter implements SendSmsOutPort {
    @Override
    public void notifyCustomer(SendSmsOutCommand command)
    {
        System.out.println("TwilioSmsAdapter: notifyCustomer");
    }
}
