package applicant.employee.application.adapter.in.camunda;

import applicant.employee.application.application.port.in.appendToList.AppendToListInCommand;
import applicant.employee.application.application.port.in.appendToList.AppendToListResult;
import applicant.employee.application.application.port.in.appendToList.AppendToListUseCase;
import io.miragon.miranum.connect.worker.api.Worker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MiranumItemToListAdapter {
    private final AppendToListUseCase appendToListUseCase;

    @Worker(type = "addItemToList")
    public AppendToListResult triggerPayrollHandling(AppendToListInCommand appendToListInCommand) {
        return appendToListUseCase.appendToList(appendToListInCommand);
    }
}
