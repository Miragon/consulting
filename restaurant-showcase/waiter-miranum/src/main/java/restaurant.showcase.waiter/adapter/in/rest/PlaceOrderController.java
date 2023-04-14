package restaurant.showcase.waiter.adapter.in.rest;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderRequestDto;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderResponseDto;
import restaurant.showcase.waiter.application.port.in.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.PlaceOrderUseCase;

@RestController
@RequestMapping("/order")
@Log4j2
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
        String message = String.format("Thanks, %s! We'll prepare your %s to %s!",
                placeOrderRequestDto.getCustomerName(),
                placeOrderRequestDto.getMeal(),
                placeOrderRequestDto.getDiningOption());
        log.info("[{}] {}", orderId, message);
        return new ResponseEntity<>(new PlaceOrderResponseDto(orderId, message), HttpStatus.CREATED);
    }
}
