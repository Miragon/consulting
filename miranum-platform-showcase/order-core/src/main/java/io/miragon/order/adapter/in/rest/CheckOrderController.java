package io.miragon.order.adapter.in.rest;

import io.miragon.order.application.port.in.GetOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class CheckOrderController
{
    private final GetOrderUseCase getOrderUseCase;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> checkOrder(@PathVariable String orderId)
    {
        var order = getOrderUseCase.getOrder(new GetOrderUseCase.GetOrderQuery(orderId));
        if (order == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        OrderDto response = new OrderDto(
                order.getItems(),
                order.getStatus().toString(),
                order.getCustomerName(),
                order.getCustomerAddress()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("/check/{orderId}")
    public ResponseEntity<Boolean> completeCheck(@PathVariable String orderId, @RequestBody boolean isAccepted)
    {
        if (isAccepted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
