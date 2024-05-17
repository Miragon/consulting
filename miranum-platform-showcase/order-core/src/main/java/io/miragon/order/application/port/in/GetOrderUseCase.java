package io.miragon.order.application.port.in;

import io.miragon.order.domain.Order;
import lombok.Getter;

import java.util.UUID;

public interface GetOrderUseCase
{
    Order getOrder(GetOrderQuery query);

    @Getter
    class GetOrderQuery
    {
        private final UUID orderId;

        public GetOrderQuery(String orderId)
        {
            this.orderId = UUID.fromString(orderId);
        }
    }
}
