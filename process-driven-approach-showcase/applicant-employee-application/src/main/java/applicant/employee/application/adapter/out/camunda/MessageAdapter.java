package applicant.employee.application.adapter.out.camunda;

import applicant.employee.application.application.port.out.placeApplication.PlaceApplicationOutCommand;
import applicant.employee.application.application.port.out.placeApplication.PlaceApplicationOutPort;
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
public class MessageAdapter implements PlaceApplicationOutPort {
    private final MessageApi messageApi;
    private static final String MESSAGE_REFERENCE = "Message_offer_made";

    @Override
    public void placeApplication(PlaceApplicationOutCommand placeApplicationOutCommand) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicationId", placeApplicationOutCommand.getApplication().getApplicationId());
        variables.put("applicantName", placeApplicationOutCommand.getApplication().getApplicantName());
        variables.put("applicantPhone", placeApplicationOutCommand.getApplication().getApplicantPhone());
        variables.put("applicantAddress", placeApplicationOutCommand.getApplication().getApplicantAddress());
        variables.put("applicantCity", placeApplicationOutCommand.getApplication().getPosition());

        messageApi.correlateMessage(new CorrelateMessageCommand(
                MESSAGE_REFERENCE,
                placeApplicationOutCommand.getApplication().getApplicationId(),
                variables));
        log.info("[{}] Sent '{}'-Message to Process Engine.",
                placeApplicationOutCommand.getApplication().getApplicationId(),
                MESSAGE_REFERENCE);
    }
}
