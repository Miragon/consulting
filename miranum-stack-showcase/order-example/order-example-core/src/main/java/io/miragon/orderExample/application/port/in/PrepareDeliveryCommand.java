package io.miragon.orderExample.application.port.in;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplateProperty;
import io.miragon.miranum.connect.elementtemplate.api.PropertyType;
import io.miragon.orderExample.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrepareDeliveryCommand
{
    @ElementTemplateProperty(type = PropertyType.STRING, label = "Items", notEmpty = true)
    private List<Item> items;
}
