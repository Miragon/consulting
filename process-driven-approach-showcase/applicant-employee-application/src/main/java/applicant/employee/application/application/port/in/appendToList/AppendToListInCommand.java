package applicant.employee.application.application.port.in.appendToList;

import lombok.Getter;

import java.util.List;

@Getter
public class AppendToListInCommand {
    private String applicantName;
    private String applicantAddress;
    private String applicantPhone;
    private String position;
    private List<String> payrollList;
}
