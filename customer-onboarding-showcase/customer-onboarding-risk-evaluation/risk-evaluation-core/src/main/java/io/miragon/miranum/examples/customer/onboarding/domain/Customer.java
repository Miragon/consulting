package io.miragon.miranum.examples.customer.onboarding.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Customer {
    private String name;
    private String address;
    private String email;
    private String employment;
    private double income;
}
