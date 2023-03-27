package io.miragon.miranum.examples.customer.onboarding.application.port.in;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplateProperty;
import io.miragon.miranum.connect.elementtemplate.api.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EvaluateRiskCommand {

    @ElementTemplateProperty(label = "Name", type = PropertyType.STRING, notEmpty = true)
    private String name;

    @ElementTemplateProperty(label = "Address", type = PropertyType.STRING, notEmpty = true)
    private String address;
    @ElementTemplateProperty(label = "Email", type = PropertyType.STRING, notEmpty = true)
    private String email;
    @ElementTemplateProperty(label = "Phone", type = PropertyType.STRING, notEmpty = false)
    private String employment;
    @ElementTemplateProperty(type = PropertyType.STRING, label = "Income", notEmpty = true)
    private String income;
}
