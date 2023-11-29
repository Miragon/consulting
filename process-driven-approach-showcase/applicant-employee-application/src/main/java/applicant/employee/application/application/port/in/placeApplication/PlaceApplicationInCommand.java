package applicant.employee.application.application.port.in.placeApplication;

import applicant.employee.application.adapter.in.rest.dtos.PlaceApplicationRequestDto;
import lombok.Getter;

@Getter
public class PlaceApplicationInCommand {
    private final String applicantName;
    private final String applicantAddress;
    private final String applicantPhone;
    private final String position;

    public PlaceApplicationInCommand(PlaceApplicationRequestDto placeApplicationInCommand) {
        this.applicantName = placeApplicationInCommand.getApplicantName();
        this.applicantAddress = placeApplicationInCommand.getApplicantAddress();
        this.applicantPhone = placeApplicationInCommand.getApplicantPhone();
        this.position = placeApplicationInCommand.getPosition();
    }
}
