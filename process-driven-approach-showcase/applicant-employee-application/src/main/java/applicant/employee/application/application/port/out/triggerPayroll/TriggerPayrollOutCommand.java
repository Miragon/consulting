package applicant.employee.application.application.port.out.triggerPayroll;

import applicant.employee.application.domain.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TriggerPayrollOutCommand {
    private Application application;
}
