package io.miragon.order.adapter.in.rest;

import io.miragon.order.application.port.in.PlaceOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/place-order")
@AllArgsConstructor
public class PlaceOrderController
{
    private final PlaceOrderUseCase placeOrderUseCase;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<UUID> placeOrder(@RequestBody OrderDto orderDto)
    {
        PlaceOrderUseCase.PlaceOrderCommand command = new PlaceOrderUseCase.PlaceOrderCommand(
                orderDto.getCustomerName(),
                orderDto.getCustomerAddress(),
                orderDto.getItems()
        );

        try {
            var orderId = placeOrderUseCase.placeOrder(command);
            return new ResponseEntity<>(orderId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
