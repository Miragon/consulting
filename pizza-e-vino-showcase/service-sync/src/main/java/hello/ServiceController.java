package hello;

import hello.service.OrderRO;
import hello.service.PizzaOrderService;
import hello.service.VinoOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ServiceController {

    VinoOrderService vinoOrderService;
    PizzaOrderService pizzaOrderService;

    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public String index(@RequestBody OrderRO orderRequest) throws Exception {

        System.out.printf("Got this order for %s: %s%n", orderRequest.getCustomerName(), orderRequest.getOrderMessage());
        String orderMessage = "";

        if(orderRequest.getOrderMessage().toLowerCase().contains("vino")){
            orderMessage = orderMessage + " Vino ";
            vinoOrderService.execute(orderRequest);
        }
        if(orderRequest.getOrderMessage().toLowerCase().contains("pizza")){
            orderMessage = orderMessage + " Pizza ";
            pizzaOrderService.execute(orderRequest);
        }

        return "Order of " + orderMessage + " Is Ready";
    }
}
