package applicant.employee.application.domain;

import applicant.employee.application.application.port.in.placeApplication.PlaceApplicationInCommand;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Application {
    private final String applicantName;
    private final String applicantAddress;
    private final String applicantPhone;
    private final String position;
    private final String applicationId;

    public Application(String applicantName, String applicantAddress, String applicantPhone, String position) {
        this.applicantName = applicantName;
        this.applicantAddress = applicantAddress;
        this.applicantPhone = applicantPhone;
        this.position = position;
        this.applicationId = String.format("order-%s-%s", applicantName.toLowerCase().replaceAll("\\s", ""), LocalDateTime.now());;
    }
}
