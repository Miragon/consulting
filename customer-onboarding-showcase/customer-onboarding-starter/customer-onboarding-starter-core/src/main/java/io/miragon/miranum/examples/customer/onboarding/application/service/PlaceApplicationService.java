package io.miragon.miranum.examples.customer.onboarding.application.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.PlaceApplicationInCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.PlaceApplicationUseCase;
import io.miragon.miranum.examples.customer.onboarding.application.port.out.PlaceApplicationOutCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.out.PlaceApplicationPort;

import java.util.Map;

public class PlaceApplicationService implements PlaceApplicationUseCase {

    private final PlaceApplicationPort placeApplicationPort;
    private final String processId = "customer-onboarding";

    public PlaceApplicationService(PlaceApplicationPort placeApplicationPort) {
        this.placeApplicationPort = placeApplicationPort;
    }

    @Override
    public void placeApplication(PlaceApplicationInCommand placeApplicationInCommand) {
        var objectMapper = new ObjectMapper();
        Map<String, Object> variables = objectMapper.convertValue(placeApplicationInCommand, new TypeReference<>() {
        });
        var placeOrderOutCommand = new PlaceApplicationOutCommand(processId, variables);
        placeApplicationPort.placeApplication(placeOrderOutCommand);
    }
}