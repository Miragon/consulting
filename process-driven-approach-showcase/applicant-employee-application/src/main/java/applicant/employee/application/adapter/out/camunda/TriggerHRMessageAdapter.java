package applicant.employee.application.adapter.out.camunda;

import applicant.employee.application.application.port.out.triggerHR.TriggerHROutCommand;
import applicant.employee.application.application.port.out.triggerHR.TriggerHROutPort;
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
public class TriggerHRMessageAdapter implements TriggerHROutPort {
    private final MessageApi messageApi;
    private static final String MESSAGE_REFERENCE = "Message_add_to_HR_pool";

    @Override
    public void triggerHR(TriggerHROutCommand triggerHROutCommand) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicationId", triggerHROutCommand.getApplication().getApplicationId());
        variables.put("applicantName", triggerHROutCommand.getApplication().getApplicantName());
        variables.put("applicantPhone", triggerHROutCommand.getApplication().getApplicantPhone());
        variables.put("applicantAddress", triggerHROutCommand.getApplication().getApplicantAddress());
        variables.put("position", triggerHROutCommand.getApplication().getPosition());

        messageApi.correlateMessage(new CorrelateMessageCommand(
                MESSAGE_REFERENCE,
                triggerHROutCommand.getApplication().getApplicationId(),
                variables));
        log.info("[{}] Sent '{}'-Message to Process Engine.",
                triggerHROutCommand.getApplication().getApplicationId(),
                MESSAGE_REFERENCE);
    }
}
