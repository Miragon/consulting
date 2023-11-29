package applicant.employee.application.adapter.out.camunda;

import applicant.employee.application.application.port.out.triggerHR.TriggerHROutCommand;
import applicant.employee.application.application.port.out.triggerPayroll.TriggerPayrollOutCommand;
import applicant.employee.application.application.port.out.triggerPayroll.TriggerPayrollOutPort;
import io.miragon.miranum.connect.message.api.CorrelateMessageCommand;
import io.miragon.miranum.connect.message.api.MessageApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Component
public class TriggerPayrollMessageAdapter implements TriggerPayrollOutPort {
    private final MessageApi messageApi;
    private static final String MESSAGE_REFERENCE = "Message_add_to_Payroll_pool";

    @Override
    public void triggerPayroll(TriggerPayrollOutCommand triggerPayrollOutCommand) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicationId", triggerPayrollOutCommand.getApplication().getApplicationId());
        variables.put("applicantName", triggerPayrollOutCommand.getApplication().getApplicantName());
        variables.put("applicantPhone", triggerPayrollOutCommand.getApplication().getApplicantPhone());
        variables.put("applicantAddress", triggerPayrollOutCommand.getApplication().getApplicantAddress());
        variables.put("position", triggerPayrollOutCommand.getApplication().getPosition());

        messageApi.correlateMessage(new CorrelateMessageCommand(
                MESSAGE_REFERENCE,
                triggerPayrollOutCommand.getApplication().getApplicationId(),
                variables));
        log.info("[{}] Sent '{}'-Message to Process Engine.",
                triggerPayrollOutCommand.getApplication().getApplicationId(),
                MESSAGE_REFERENCE);
    }
}
