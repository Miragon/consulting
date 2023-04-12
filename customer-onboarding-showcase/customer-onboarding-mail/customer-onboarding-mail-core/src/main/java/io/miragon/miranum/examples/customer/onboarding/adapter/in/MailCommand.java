package io.miragon.miranum.examples.customer.onboarding.adapter.in;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplateProperty;
import io.miragon.miranum.connect.elementtemplate.api.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MailCommand {
    @ElementTemplateProperty(type = PropertyType.STRING, notEmpty = true, label = "Name")
    private String name;
    @ElementTemplateProperty(type = PropertyType.STRING, notEmpty = true, label = "Email")
    private String email;
    @ElementTemplateProperty(type = PropertyType.STRING, notEmpty = true, label = "Subject")
    private String content;
}