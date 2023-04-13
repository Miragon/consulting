package restaurant.showcase.waiter.adapter.out.process;

import io.miragon.miranum.connect.process.api.ProcessApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import restaurant.showcase.waiter.application.port.out.PlaceOrderPort;

@Configuration
public class ProcessAdapterAutoConfiguration {

    @Bean
    public PlaceOrderPort placeOrderPort(final ProcessApi processApi) {
        return new ProcessAdapter(processApi);
    }
}
