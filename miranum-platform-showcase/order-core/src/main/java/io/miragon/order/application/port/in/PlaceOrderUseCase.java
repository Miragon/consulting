package io.miragon.order.application.port.in;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

public interface PlaceOrderUseCase
{
    UUID placeOrder(PlaceOrderCommand placeOrderCommand);

    @Getter
    class PlaceOrderCommand
    {
        private final String customerName;

        private final String customerAddress;

        private final Map<String, Integer> items;

        public PlaceOrderCommand(String customerName, String customerAddress, Map<String, Integer> items)
        {
            this.customerName = customerName;
            this.customerAddress = customerAddress;
            this.items = items;
        }
    }
}
