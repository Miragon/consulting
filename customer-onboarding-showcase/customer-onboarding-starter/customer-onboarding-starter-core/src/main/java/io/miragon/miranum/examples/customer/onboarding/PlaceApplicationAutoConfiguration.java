package io.miragon.miranum.examples.customer.onboarding;

import io.miragon.miranum.examples.customer.onboarding.adapter.out.ProcessAdapterAutoConfiguration;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.PlaceApplicationUseCase;
import io.miragon.miranum.examples.customer.onboarding.application.port.out.PlaceApplicationPort;
import io.miragon.miranum.examples.customer.onboarding.application.service.PlaceApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ProcessAdapterAutoConfiguration.class
})
public class PlaceApplicationAutoConfiguration {
    @Bean
    public PlaceApplicationUseCase placeApplicationUseCase(final PlaceApplicationPort placeApplicationPort) {
        return new PlaceApplicationService(placeApplicationPort);
    }
}