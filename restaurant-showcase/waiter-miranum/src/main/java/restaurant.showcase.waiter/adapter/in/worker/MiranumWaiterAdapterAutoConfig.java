package restaurant.showcase.waiter.adapter.in.worker;

import org.springframework.context.annotation.Configuration;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealUseCase;

@Configuration
public class MiranumWaiterAdapterAutoConfig {

    public MiranumWaiterAdapter miranumWaiterAdapter(final PrepareMealUseCase prepareMealUseCase) {
        return new MiranumWaiterAdapter(prepareMealUseCase);
    }
}
