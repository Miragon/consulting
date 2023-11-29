package applicant.employee.application.application.service;

import applicant.employee.application.application.port.in.placeApplication.PlaceApplicationInCommand;
import applicant.employee.application.application.port.in.placeApplication.PlaceApplicationUseCase;
import applicant.employee.application.application.port.out.placeApplication.PlaceApplicationOutCommand;
import applicant.employee.application.application.port.out.placeApplication.PlaceApplicationOutPort;
import applicant.employee.application.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceApplicationService implements PlaceApplicationUseCase {
    private final PlaceApplicationOutPort placeApplicationPort;

    @Override
    public String placeApplication(PlaceApplicationInCommand placeApplicationInCommand) {
        var application = new Application(placeApplicationInCommand.getApplicantName(), placeApplicationInCommand.getApplicantAddress(), placeApplicationInCommand.getApplicantPhone(), placeApplicationInCommand.getPosition());
        var placeOrderOutCommand = new PlaceApplicationOutCommand(application);
        placeApplicationPort.placeApplication(placeOrderOutCommand);
        return application.getApplicationId();
    }
}
