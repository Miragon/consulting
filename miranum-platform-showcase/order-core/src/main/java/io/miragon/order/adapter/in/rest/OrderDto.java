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
    Map<String, Integer> items;
    String status = "NEW";
    private String customerName;
    private String customerAddress;
}
