package io.miragon.miranum.examples.customer.onboarding.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceApplicationOutCommand {

    private String processKey;
    private Map<String, Object> variables;
}
