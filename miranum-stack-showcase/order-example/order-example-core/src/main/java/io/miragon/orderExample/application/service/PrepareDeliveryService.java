package io.miragon.orderExample.application.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import io.miragon.orderExample.application.port.in.PrepareDeliveryCommand;
import io.miragon.orderExample.application.port.in.PrepareDeliveryResult;
import io.miragon.orderExample.application.port.in.PrepareDeliveryUseCase;
import io.miragon.orderExample.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PrepareDeliveryService implements PrepareDeliveryUseCase
{
    @Override
    public PrepareDeliveryResult prepareBillOfDelivery(PrepareDeliveryCommand prepareDeliveryCommand)
    {
        List<Item> items = prepareDeliveryCommand.getItems();
        BigDecimal valueOfItems = BigDecimal.valueOf(0.0);

        log.info("Calculate total value of items...");

        for (Item item : items) {
            valueOfItems = valueOfItems.add(BigDecimal.valueOf(item.getPrice() * item.getQuantity()));
        }

        log.info("Total value of items calculated");

        return new PrepareDeliveryResult(valueOfItems.setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
