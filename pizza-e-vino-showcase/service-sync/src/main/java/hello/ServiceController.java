package hello;

import hello.service.OrderMessageRequest;
import hello.service.SortPizzaOrderService;
import hello.service.SortVinoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    SortVinoOrderService sortVinoOrder;

    @Autowired
    SortPizzaOrderService sortPizzaOrder;

    @RequestMapping(value = "/orderUp", method = RequestMethod.POST)
    public String index(@RequestBody OrderMessageRequest orderMessageRequest) throws Exception {

        System.out.println("Got this message for Mike: " + orderMessageRequest.orderMessage);
        // Map<String, Object> vars = new HashMap<String, Object>();
        String orderMessage = "";

        if(orderMessageRequest.orderMessage.toLowerCase().contains("vino")){
            orderMessage = orderMessage + " Vino ";
            sortVinoOrder.execute();
        }
        if(orderMessageRequest.orderMessage.toLowerCase().contains("pizza")){
            orderMessage = orderMessage + " Pizza ";
            sortPizzaOrder.execute();
        }

        return "Order of " + orderMessage + " Is Ready";
    }
}
