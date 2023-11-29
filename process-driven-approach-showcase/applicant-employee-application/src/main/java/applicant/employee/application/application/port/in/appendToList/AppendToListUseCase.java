package applicant.employee.application.application.port.in.appendToList;

import applicant.employee.application.application.port.in.placeApplication.PlaceApplicationInCommand;

public interface AppendToListUseCase {
    AppendToListResult appendToList(AppendToListInCommand appendToListInCommand);
}
