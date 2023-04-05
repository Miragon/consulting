package checkout;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RestController
public class OrderController {
    private final RestTemplate restTemplate;

    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    public OrderController() {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String sendOrder(@RequestBody OrderRO order){
        System.out.println("-- Sending order  "+ order.getCustomerName() + " to our " + "Order Sorter -- >");
        URI uri = URI.create("http://localhost:8080/order/");
        String response = restTemplate.postForObject(uri, order, String.class);
        System.out.println("-- All good - the order has been sent -- ");
        return response;
    }


}
