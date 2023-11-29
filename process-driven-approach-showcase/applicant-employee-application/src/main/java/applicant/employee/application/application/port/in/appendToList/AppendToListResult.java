package applicant.employee.application.application.port.in.appendToList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AppendToListResult {
    private List<String> payrollList;
}
