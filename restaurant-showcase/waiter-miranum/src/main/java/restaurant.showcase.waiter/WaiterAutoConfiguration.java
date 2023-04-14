package restaurant.showcase.waiter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import restaurant.showcase.waiter.adapter.in.worker.MiranumWaiterAdapterAutoConfig;
import restaurant.showcase.waiter.adapter.out.process.ProcessAdapterAutoConfiguration;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderUseCase;
import restaurant.showcase.waiter.application.port.out.placeOrder.PlaceOrderPort;
import restaurant.showcase.waiter.application.service.PlaceOrderService;

@Configuration
@Import({
        MiranumWaiterAdapterAutoConfig.class,
        ProcessAdapterAutoConfiguration.class
})
public class WaiterAutoConfiguration {

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(final PlaceOrderPort placeOrderPort) {
        return new PlaceOrderService(placeOrderPort);
    }
}
