package io.miragon.orderExample.application.port.in;

public interface PrepareDeliveryUseCase
{
    PrepareDeliveryResult prepareBillOfDelivery(PrepareDeliveryCommand prepareDeliveryCommand);
}
