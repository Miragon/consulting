package applicant.employee.application.adapter.in.rest;

import applicant.employee.application.adapter.in.rest.dtos.PlaceApplicationRequestDto;
import applicant.employee.application.adapter.in.rest.dtos.PlaceApplicationResponseDto;
import applicant.employee.application.application.port.in.PlaceApplicationInCommand;
import applicant.employee.application.application.port.in.PlaceApplicationUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class ApplicationController {
    private final PlaceApplicationUseCase placeApplicationUseCase;

    @PostMapping
    @RequestMapping("/order")
    public ResponseEntity<PlaceApplicationResponseDto> placeOrder(@RequestBody PlaceApplicationRequestDto placeApplicationRequestDto) {
        var placeApplicationInCommand = new PlaceApplicationInCommand(placeApplicationRequestDto);
        String applicationId = placeApplicationUseCase.placeApplication(placeApplicationInCommand);
        String message = String.format("Thanks for submitting the request! We'll work on hiring %s for the position %s!",
                placeApplicationRequestDto.getApplicantName(),
                placeApplicationRequestDto.getPosition());

        log.info("[{}] {}", applicationId, message);
        return new ResponseEntity<>(new PlaceApplicationResponseDto(applicationId, message), HttpStatus.CREATED);
    }
}
