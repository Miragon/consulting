package applicant.employee.application.application.port.out.formFeedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FormFeedbackOutCommand {
    private String applicationId;
    private String message;
}
