package io.miragon.miranum.examples.customer.onboarding.adapter.out;

import io.miragon.miranum.connect.process.api.ProcessApi;
import io.miragon.miranum.examples.customer.onboarding.application.port.out.PlaceApplicationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessAdapterAutoConfiguration {

    @Bean
    public PlaceApplicationPort placeApplicationPort(final ProcessApi processApi) {
        return new ProcessAdapter(processApi);
    }
}