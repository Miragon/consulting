package io.miragon.order.application.port.in;

import lombok.Getter;

import java.util.UUID;

public interface PrepareDeliveryUseCase
{
    void prepareDelivery(PrepareDeliveryCommand command);

    @Getter
    class PrepareDeliveryCommand
    {
        private final UUID orderId;

        public PrepareDeliveryCommand(String orderId) {
            this.orderId = UUID.fromString(orderId);
        }
    }
}
