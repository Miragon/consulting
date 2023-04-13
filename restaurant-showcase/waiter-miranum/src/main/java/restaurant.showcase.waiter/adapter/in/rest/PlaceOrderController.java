package restaurant.showcase.waiter.adapter.in.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderRequestDto;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderResponseDto;
import restaurant.showcase.waiter.application.port.in.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.PlaceOrderUseCase;

@RestController
@RequestMapping("/api/place-order")
@AllArgsConstructor
public class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;

    @PostMapping
    public ResponseEntity<PlaceOrderResponseDto> placeOrder(@RequestBody PlaceOrderRequestDto placeOrderRequestDto) {
        var placeOrderInCommand = new PlaceOrderInCommand(
                placeOrderRequestDto.getCustomerName(),
                placeOrderRequestDto.getMeal(),
                placeOrderRequestDto.getDiningOption());
        String orderId = placeOrderUseCase.placeOrder(placeOrderInCommand);
        return new ResponseEntity<>(new PlaceOrderResponseDto(orderId), HttpStatus.CREATED);
    }
}
