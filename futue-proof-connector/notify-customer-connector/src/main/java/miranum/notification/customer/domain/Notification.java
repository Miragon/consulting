package miranum.notification.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Notification {
    private final String notificationMethod;
    private final String topic;
    private final String message;
    private final Customer customer;
}
