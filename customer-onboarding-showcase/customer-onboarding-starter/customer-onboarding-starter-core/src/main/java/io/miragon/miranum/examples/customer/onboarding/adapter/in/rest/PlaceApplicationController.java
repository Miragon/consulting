package io.miragon.miranum.examples.customer.onboarding.adapter.in.rest;

import io.miragon.miranum.examples.customer.onboarding.adapter.in.rest.dtos.PlaceApplicationRequestDto;
import io.miragon.miranum.examples.customer.onboarding.adapter.in.rest.dtos.PlaceApplicationResponseDto;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.PlaceApplicationInCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.PlaceApplicationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/place-application")
@AllArgsConstructor
@CrossOrigin
public class PlaceApplicationController {

    private final PlaceApplicationUseCase placeApplicationUseCase;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<PlaceApplicationResponseDto> placeApplication(@RequestBody PlaceApplicationRequestDto placeApplicationRequestDto) {
        var placeApplicationInCommand = new PlaceApplicationInCommand(
                placeApplicationRequestDto.getName(),
                placeApplicationRequestDto.getEmail(),
                placeApplicationRequestDto.getAddress(),
                placeApplicationRequestDto.getIncome(),
                placeApplicationRequestDto.getEmployment());
        placeApplicationUseCase.placeApplication(placeApplicationInCommand);
        return new ResponseEntity<>(new PlaceApplicationResponseDto(
                placeApplicationInCommand.getName(),
                placeApplicationInCommand.getEmail(),
                placeApplicationRequestDto.getAddress(),
                placeApplicationRequestDto.getIncome(),
                placeApplicationRequestDto.getEmployment()
        ), HttpStatus.CREATED);
    }
}