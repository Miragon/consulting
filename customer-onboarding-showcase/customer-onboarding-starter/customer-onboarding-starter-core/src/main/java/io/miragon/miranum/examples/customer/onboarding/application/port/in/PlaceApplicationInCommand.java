package io.miragon.miranum.examples.customer.onboarding.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceApplicationInCommand {

    private String name;
    private String email;
    private String address;
    private String income;
    private String employment;
}