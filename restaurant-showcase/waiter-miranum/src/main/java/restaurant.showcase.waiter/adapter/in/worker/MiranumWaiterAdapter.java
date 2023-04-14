package restaurant.showcase.waiter.adapter.in.worker;

import io.miragon.miranum.connect.worker.api.Worker;
import lombok.AllArgsConstructor;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealInCommand;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealUseCase;

@AllArgsConstructor
public class MiranumWaiterAdapter {

    private final PrepareMealUseCase prepareMealUseCase;

    @Worker(type = "prepareMeal")
    public void prepareMeal(PrepareMealInCommand prepareMealInCommand) {
        prepareMealUseCase.prepareMeal(prepareMealInCommand);
    }

}
