package io.miragon.order.adapter.in.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto
{
    private String customerName;

    private String customerAddress;

    Map<String, Integer> items;
}
