package miranum.notification.customer.application.port.out.sendMail;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendMailOutCommand {
    private final String customerName;
    private final String customerEmail;
    private final String topic;
    private final String message;
}