package pizza.sync;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pizza.sync.service.OrderRO;
import pizza.sync.service.PizzaOrderService;
import pizza.sync.service.VinoOrderService;

@RestController
@AllArgsConstructor
public class ServiceController {

    VinoOrderService vinoOrderService;
    PizzaOrderService pizzaOrderService;

    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public String handleOrder(@RequestBody OrderRO orderRO) throws Exception {

        System.out.printf("Got this order for %s: %s %n", orderRO.getCustomerName(), orderRO.getOrderMessage());
        String orderMessage = "";

        if (orderRO.getOrderMessage().toLowerCase().contains("vino")) {
            vinoOrderService.execute(orderRO);
            orderMessage = orderMessage + " Here is your Vino!";
        }

        if (orderRO.getOrderMessage().toLowerCase().contains("pizza/async")) {
            pizzaOrderService.execute(orderRO);
            orderMessage = orderMessage + " Here is your Pizza!";
        }

        if (orderMessage.isEmpty()) {
            orderMessage = " Sorry, I can't do that for you. Order pizza and vino, please.";
        }

        String response = String.format("Hi, %s!%s", orderRO.getCustomerName(), orderMessage);

        System.out.println(response);
        return response;
    }
}
