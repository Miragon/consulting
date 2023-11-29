package applicant.employee.application.application.port.in.triggerPayroll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TriggerPayrollInCommand {
    private String applicantName;
    private String applicantAddress;
    private String applicantPhone;
    private String position;
}
