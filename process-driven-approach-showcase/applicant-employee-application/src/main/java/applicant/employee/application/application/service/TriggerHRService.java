package applicant.employee.application.application.service;

import applicant.employee.application.application.port.in.triggerHR.TriggerHRInCommand;
import applicant.employee.application.application.port.in.triggerHR.TriggerHRUseCase;
import applicant.employee.application.application.port.out.triggerHR.TriggerHROutCommand;
import applicant.employee.application.application.port.out.triggerHR.TriggerHROutPort;
import applicant.employee.application.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TriggerHRService  implements TriggerHRUseCase {
    private final TriggerHROutPort triggerHRPort;

    @Override
    public void triggerHR(TriggerHRInCommand triggerHRInCommand) {
        var application = new Application(triggerHRInCommand.getApplicantName(), triggerHRInCommand.getApplicantAddress(), triggerHRInCommand.getApplicantPhone(), triggerHRInCommand.getPosition());
        var triggerHROutCommand = new TriggerHROutCommand(application);
        triggerHRPort.triggerHR(triggerHROutCommand);
    }
}
