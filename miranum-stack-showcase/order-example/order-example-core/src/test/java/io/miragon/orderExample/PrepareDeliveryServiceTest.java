package io.miragon.orderExample;

import io.miragon.orderExample.application.port.in.PrepareDeliveryCommand;
import io.miragon.orderExample.application.service.PrepareDeliveryService;
import io.miragon.orderExample.domain.Item;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@Import(PrepareDeliveryService.class)
@ExtendWith(SpringExtension.class)
public class PrepareDeliveryServiceTest
{
    @Autowired
    private PrepareDeliveryService prepareDeliveryService;

    @Test
    public void shouldAddUpValueOfItems() {
        List<Item> items = Arrays.asList(
                new Item("Soda", 10, 3.99),
                new Item("Chips", 12, 1.79),
                new Item("Chocolate", 2, 2.99));

        Assertions.assertEquals(
                prepareDeliveryService.prepareBillOfDelivery(new PrepareDeliveryCommand(items)).getValueOfItems(),
                67.36);
    }
}
