package io.miragon.orderExample;

import io.miragon.orderExample.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.orderExample.application.port.in.PrepareDeliveryUseCase;
import io.miragon.orderExample.application.service.PrepareDeliveryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumInAutoConfiguration.class
})
public class OrderExampleAutoConfiguration
{
    @Bean
    public PrepareDeliveryUseCase prepareDeliveryUseCase() {
        return new PrepareDeliveryService();
    }
}
