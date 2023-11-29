package applicant.employee.application.application.port.out.triggerHR;

import applicant.employee.application.domain.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TriggerHROutCommand {
    private Application application;
}
