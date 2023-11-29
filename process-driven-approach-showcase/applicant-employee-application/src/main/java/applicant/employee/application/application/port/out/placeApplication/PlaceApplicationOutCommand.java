package applicant.employee.application.application.port.out.placeApplication;

import applicant.employee.application.domain.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceApplicationOutCommand {
    private Application application;
}
