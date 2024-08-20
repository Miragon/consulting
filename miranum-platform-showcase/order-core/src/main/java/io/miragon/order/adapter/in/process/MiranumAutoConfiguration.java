package io.miragon.order.adapter.in.process;

import io.miragon.order.application.port.in.DeliverOrderUseCase;
import io.miragon.order.application.port.in.PrepareDeliveryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiranumAutoConfiguration
{
    @Bean
    public MiranumWorkerAdapter miranumWorkerAdapter(
            final PrepareDeliveryUseCase prepareDeliveryUseCase,
            final DeliverOrderUseCase deliverOrderUseCase
    )
    {
        return new MiranumWorkerAdapter(prepareDeliveryUseCase, deliverOrderUseCase);
    }
}
