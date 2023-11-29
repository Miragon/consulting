package applicant.employee.application.application.port.in.triggerHR;

import applicant.employee.application.adapter.in.rest.dtos.PlaceApplicationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TriggerHRInCommand {
    private String applicantName;
    private String applicantAddress;
    private String applicantPhone;
    private String position;
}