package restaurant.showcase.waiter.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DiningOption {
    DINE_IN("dine-in"),
    Take_AWAY("take-away");

    final String name;

    public static DiningOption fromString(String name) {
        return Arrays.stream(DiningOption.values())
                .filter(diningOption -> diningOption.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such dining option: " + name));
    }
}
