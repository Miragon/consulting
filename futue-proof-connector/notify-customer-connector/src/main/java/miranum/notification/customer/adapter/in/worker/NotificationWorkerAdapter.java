package miranum.notification.customer.adapter.in.worker;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplate;
import io.miragon.miranum.connect.worker.api.Worker;
import lombok.AllArgsConstructor;
import miranum.notification.customer.application.port.in.NotifyCustomerInCommand;
import miranum.notification.customer.application.port.in.NotifyCustomerUseCase;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NotificationWorkerAdapter {
    private final NotifyCustomerUseCase notifyCustomerUseCase;

    @Worker(type = "notifyCustomer")
    @ElementTemplate(name = "Notify Customer", description = "Send a notification to the customer.")
    public void prepareMeal(NotifyCustomerInCommand notifyCustomerInCommand) {
        notifyCustomerUseCase.notifyCustomer(notifyCustomerInCommand);
    }
}
