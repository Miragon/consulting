package io.miragon.order.adapter.in.process;

import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.order.application.port.in.DeliverOrderUseCase;
import io.miragon.order.application.port.in.PrepareDeliveryUseCase;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class MiranumWorkerAdapter
{
    private final PrepareDeliveryUseCase prepareDeliveryUseCase;

    private final DeliverOrderUseCase deliverOrderUseCase;

    @Worker(type = "prepareDelivery")
    public void prepareDelivery(Map<String, Object> variables)
    {
        var orderId = (String) variables.get("orderId");
        PrepareDeliveryUseCase.PrepareDeliveryCommand command = new PrepareDeliveryUseCase.PrepareDeliveryCommand(orderId);
        prepareDeliveryUseCase.prepareDelivery(command);
    }

    @Worker(type = "deliverItems")
    public void deliverItems(Map<String, Object> variables)
    {
        var orderId = (String) variables.get("orderId");
        DeliverOrderUseCase.DeliverItemsCommand command = new DeliverOrderUseCase.DeliverItemsCommand(orderId);
        deliverOrderUseCase.deliverItems(command);
    }
}
