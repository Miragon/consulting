package restaurant.showcase.waiter.adapter.out.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestConfig {

    @Value("${application.rest.kitchen}")
    private String kitchenEndpoint;

    @Value("${application.rest.payment}")
    private String paymentEndpoint;

    @Bean
    public WebClient kitchenApiClient() {
        return WebClient.create(kitchenEndpoint);
    }
    @Bean
    public WebClient paymentApiClient() {
        return WebClient.create(paymentEndpoint);
    }
}
