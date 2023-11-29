package applicant.employee.application.adapter.in.camunda;

import applicant.employee.application.application.port.in.triggerHR.TriggerHRInCommand;
import applicant.employee.application.application.port.in.triggerHR.TriggerHRUseCase;
import applicant.employee.application.application.port.in.triggerPayroll.TriggerPayrollInCommand;
import applicant.employee.application.application.port.in.triggerPayroll.TriggerPayrollUseCase;
import io.miragon.miranum.connect.worker.api.Worker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MiranumInterProcessCommunicationAdapter {
    private final TriggerPayrollUseCase triggerPayrollUseCase;
    private final TriggerHRUseCase triggerHRUseCase;


    @Worker(type = "interProcessCommunication_payroll")
    public void triggerPayrollHandling(TriggerPayrollInCommand triggerPayrollInCommand) {
        triggerPayrollUseCase.triggerPayroll(triggerPayrollInCommand);
    }

    @Worker(type = "interProcessCommunication_hr")
    public void triggerHRHandling(TriggerHRInCommand triggerHRInCommand) {
        triggerHRUseCase.triggerHR(triggerHRInCommand);
    }
}
