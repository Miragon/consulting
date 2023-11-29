package applicant.employee.application.application.port.out.triggerHR;

import applicant.employee.application.application.port.out.placeApplication.PlaceApplicationOutCommand;

public interface TriggerHROutPort {
    void triggerHR(TriggerHROutCommand triggerHROutCommand);
}