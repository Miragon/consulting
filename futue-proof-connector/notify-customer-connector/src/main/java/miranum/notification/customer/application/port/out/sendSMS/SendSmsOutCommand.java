package miranum.notification.customer.application.port.out.sendSMS;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendSmsOutCommand{
    private final String customerName;
    private final String customerPhone;
    private final String topic;
    private final String message;
}
