package applicant.employee.application.adapter.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceApplicationRequestDto {
    private String applicantName;
    private String applicantAddress;
    private String applicantPhone;
    private String position;
}
