package restaurant.showcase.waiter.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Food {
    HAMBURGER("hamburger"),
    CHEESEBURGER("cheeseburger"),
    PIZZA_MARGARITA("pizza-margarita"),
    PIZZA_PROSCIUTTO("pizza-prosciutto");

    final String name;

    public static Food fromString(String name) {
        return Arrays.stream(Food.values())
                .filter(food -> food.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such food: " + name));
    }
}
