package miranum.notification.customer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum NotificationMethod {
    EMAIL,
    SMS;

    public static NotificationMethod fromString(String name) {
        return Arrays.stream(NotificationMethod.values())
                .filter(notificationMethod -> notificationMethod.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such dining option: " + name));
    }
}
