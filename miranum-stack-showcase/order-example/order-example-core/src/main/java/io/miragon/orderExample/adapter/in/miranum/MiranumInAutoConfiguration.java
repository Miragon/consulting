package io.miragon.orderExample.adapter.in.miranum;

import io.miragon.orderExample.application.port.in.PrepareDeliveryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiranumInAutoConfiguration {

    @Bean
    public MiranumWorkerAdapter miranumWorkerAdapter(final PrepareDeliveryUseCase prepareDeliveryUseCase) {
        return new MiranumWorkerAdapter(prepareDeliveryUseCase);
    }
}
