package applicant.employee.application.application.service;

import applicant.employee.application.application.port.in.triggerHR.TriggerHRInCommand;
import applicant.employee.application.application.port.in.triggerPayroll.TriggerPayrollInCommand;
import applicant.employee.application.application.port.in.triggerPayroll.TriggerPayrollUseCase;
import applicant.employee.application.application.port.out.triggerHR.TriggerHROutCommand;
import applicant.employee.application.application.port.out.triggerPayroll.TriggerPayrollOutCommand;
import applicant.employee.application.application.port.out.triggerPayroll.TriggerPayrollOutPort;
import applicant.employee.application.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TriggerPayrollService implements TriggerPayrollUseCase {
    private final TriggerPayrollOutPort triggerPayrollPort;

    @Override
    public void triggerPayroll(TriggerPayrollInCommand triggerPayrollInCommand) {
        var application = new Application(triggerPayrollInCommand.getApplicantName(), triggerPayrollInCommand.getApplicantAddress(), triggerPayrollInCommand.getApplicantPhone(), triggerPayrollInCommand.getPosition());
        var triggerPayrollOutCommand = new TriggerPayrollOutCommand(application);
        triggerPayrollPort.triggerPayroll(triggerPayrollOutCommand);
    }
}
