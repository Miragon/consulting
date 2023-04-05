package pizza.sync;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pizza.sync.service.OrderRO;
import pizza.sync.service.PizzaOrderService;
import pizza.sync.service.VinoOrderService;

import static java.util.Objects.isNull;

@RestController
@AllArgsConstructor
public class ServiceController {

    VinoOrderService vinoOrderService;
    PizzaOrderService pizzaOrderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String handleOrder(@RequestBody OrderRO orderRO) throws Exception {

        System.out.printf("Got this order for %s: Pizza %s, Vino %s %n", orderRO.getCustomerName(), orderRO.getPizza(), orderRO.getVino());
        String orderMessage = "";

        if (!isNull(orderRO.getVino()) && !orderRO.getVino().isEmpty()) {
            vinoOrderService.execute(orderRO);
            orderMessage = orderMessage + " Here is your Vino!";
        }

        if (!isNull(orderRO.getPizza()) && !orderRO.getPizza().isEmpty()) {
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
