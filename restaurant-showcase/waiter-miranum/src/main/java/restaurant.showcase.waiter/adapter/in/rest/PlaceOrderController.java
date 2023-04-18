package restaurant.showcase.waiter.adapter.in.rest;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PayMealRequestDto;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PayMealResponseDto;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderRequestDto;
import restaurant.showcase.waiter.adapter.in.rest.dtos.PlaceOrderResponseDto;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealInCommand;
import restaurant.showcase.waiter.application.port.in.payMeal.PayMealUseCase;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderInCommand;
import restaurant.showcase.waiter.application.port.in.placeOrder.PlaceOrderUseCase;

@RestController
@RequestMapping("/order")
@Log4j2
@AllArgsConstructor
public class PlaceOrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final PayMealUseCase payMealUseCase;

    @PostMapping
    public ResponseEntity<PlaceOrderResponseDto> placeOrder(@RequestBody PlaceOrderRequestDto placeOrderRequestDto) {
        var placeOrderInCommand = new PlaceOrderInCommand(placeOrderRequestDto);
        String orderId = placeOrderUseCase.placeOrder(placeOrderInCommand);
        String message = String.format("Thanks, %s! We'll prepare your %s to %s!",
                placeOrderRequestDto.getCustomerName(),
                placeOrderRequestDto.getMeal(),
                placeOrderRequestDto.getDiningOption());
        log.info("[{}] {}", orderId, message);
        return new ResponseEntity<>(new PlaceOrderResponseDto(orderId, message), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<PayMealResponseDto> pay(@RequestBody PayMealRequestDto payMealRequestDto) {
        var payMealInCommand = new PayMealInCommand(payMealRequestDto);
        payMealUseCase.payMeal(payMealInCommand);
        String message = "Thank you. We received your payment.";
        log.info("[{}] {}", payMealInCommand.getOrderId(), message);
        return ResponseEntity.ok(new PayMealResponseDto(message));
    }
}
