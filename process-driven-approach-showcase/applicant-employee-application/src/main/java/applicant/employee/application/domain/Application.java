package applicant.employee.application.domain;

import applicant.employee.application.application.port.in.PlaceApplicationInCommand;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Application {
    private final String applicantName;
    private final String applicantAddress;
    private final String applicantPhone;
    private final String position;
    private final String applicationId;

    public Application(PlaceApplicationInCommand placeApplicationInCommand) {
        this.applicantName = placeApplicationInCommand.getApplicantName();
        this.applicantAddress = placeApplicationInCommand.getApplicantAddress();
        this.applicantPhone = placeApplicationInCommand.getApplicantPhone();
        this.position = placeApplicationInCommand.getPosition();
        this.applicationId = String.format("order-%s-%s", placeApplicationInCommand.getApplicantName().toLowerCase().replaceAll("\\s", ""), LocalDateTime.now());;
    }
}
