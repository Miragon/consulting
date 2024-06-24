package io.miragon.order.application.port.in;

import lombok.Getter;

import java.util.UUID;

public interface DeliverOrderUseCase
{
    void deliverItems(DeliverItemsCommand command);

    @Getter
    class DeliverItemsCommand
    {
        private final UUID orderId;

        public DeliverItemsCommand(String orderId)
        {
            this.orderId = UUID.fromString(orderId);
        }
    }
}
