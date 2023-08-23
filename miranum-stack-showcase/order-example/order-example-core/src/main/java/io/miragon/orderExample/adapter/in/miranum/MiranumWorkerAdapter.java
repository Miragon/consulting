package io.miragon.orderExample.adapter.in.miranum;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplate;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.orderExample.application.port.in.PrepareDeliveryCommand;
import io.miragon.orderExample.application.port.in.PrepareDeliveryResult;
import io.miragon.orderExample.application.port.in.PrepareDeliveryUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MiranumWorkerAdapter {

    private final PrepareDeliveryUseCase prepareDeliveryUseCase;

    @Worker(type = "prepareDelivery")
    @ElementTemplate(name = "Prepare Delivery", description = "Prepare the bill of delivery.")
    public PrepareDeliveryResult prepareDelivery(PrepareDeliveryCommand command) {
        return prepareDeliveryUseCase.prepareBillOfDelivery(command);
    }
}
