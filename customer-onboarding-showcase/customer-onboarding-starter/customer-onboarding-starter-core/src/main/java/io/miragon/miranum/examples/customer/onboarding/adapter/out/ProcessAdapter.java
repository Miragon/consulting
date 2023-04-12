package io.miragon.miranum.examples.customer.onboarding.adapter.out;

import io.miragon.miranum.connect.process.api.ProcessApi;
import io.miragon.miranum.connect.process.api.StartProcessCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.out.PlaceApplicationOutCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.out.PlaceApplicationPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProcessAdapter implements PlaceApplicationPort {

    private final ProcessApi processApi;

    @Override
    public void placeApplication(PlaceApplicationOutCommand placeApplicationOutCommand) {
        var startProcessCommand = new StartProcessCommand(placeApplicationOutCommand.getProcessKey(), placeApplicationOutCommand.getVariables());
        processApi.startProcess(startProcessCommand);
    }
}