package applicant.employee.application.application.service;

import applicant.employee.application.application.port.in.appendToList.AppendToListInCommand;
import applicant.employee.application.application.port.in.appendToList.AppendToListUseCase;
import applicant.employee.application.application.port.in.appendToList.AppendToListResult;
import applicant.employee.application.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AppendToListService implements AppendToListUseCase {
    @Override
    public AppendToListResult appendToList(AppendToListInCommand appendToListInCommand) {
        var application = new Application(appendToListInCommand.getApplicantName(), appendToListInCommand.getApplicantAddress(), appendToListInCommand.getApplicantPhone(), appendToListInCommand.getPosition());
        List<String> payrollist;
        if(appendToListInCommand.getPayrollList() == null) {
            payrollist = new ArrayList<>();
            payrollist.add(application.toString());
        }
        else {
            payrollist = appendToListInCommand.getPayrollList();
            payrollist.add(application.toString());
        }

        return new AppendToListResult(payrollist);
    }
}
