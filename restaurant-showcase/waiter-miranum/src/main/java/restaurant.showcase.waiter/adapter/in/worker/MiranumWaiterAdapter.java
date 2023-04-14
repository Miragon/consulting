package restaurant.showcase.waiter.adapter.in.worker;

import io.miragon.miranum.connect.worker.api.Worker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import restaurant.showcase.waiter.application.port.in.handOverCheck.HandOverCheckInCommand;
import restaurant.showcase.waiter.application.port.in.handOverCheck.HandOverCheckUseCase;
import restaurant.showcase.waiter.application.port.in.issueCheck.IssueCheckInCommand;
import restaurant.showcase.waiter.application.port.in.issueCheck.IssueCheckUseCase;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealInCommand;
import restaurant.showcase.waiter.application.port.in.prepareMeal.PrepareMealUseCase;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerInCommand;
import restaurant.showcase.waiter.application.port.in.reassureCustomer.ReassureCustomerUseCase;
import restaurant.showcase.waiter.application.port.in.serveMeal.ServeMealInCommand;
import restaurant.showcase.waiter.application.port.in.serveMeal.ServeMealUseCase;

@AllArgsConstructor
@Component
public class MiranumWaiterAdapter {

    private final PrepareMealUseCase prepareMealUseCase;
    private final HandOverCheckUseCase handOverCheckUseCase;
    private final IssueCheckUseCase issueCheckUseCase;
    private final ReassureCustomerUseCase reassureCustomerUseCase;
    private final ServeMealUseCase serveMealUseCase;

    @Worker(type = "prepareMeal")
    public void prepareMeal(PrepareMealInCommand prepareMealInCommand) {
        prepareMealUseCase.prepareMeal(prepareMealInCommand);
    }

    @Worker(type = "issueCheck")
    public void issueCheck(IssueCheckInCommand issueCheckInCommand) {
        issueCheckUseCase.issueCheck(issueCheckInCommand);
    }

    @Worker(type = "handOverCheck")
    public void handOverCheck(HandOverCheckInCommand handOverCheckInCommand) {
        handOverCheckUseCase.handOverCheck(handOverCheckInCommand);
    }

    @Worker(type = "reassureCustomer")
    public void reassureCustomer(ReassureCustomerInCommand reassureCustomerInCommand) {
        reassureCustomerUseCase.reassureCustomer(reassureCustomerInCommand);
    }

    @Worker(type = "serveMeal")
    public void serveMeal(ServeMealInCommand serveMealInCommand) {
        serveMealUseCase.serveMeal(serveMealInCommand);
    }
}
