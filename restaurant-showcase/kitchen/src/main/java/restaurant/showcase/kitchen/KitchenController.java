package restaurant.showcase.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KitchenController {

    private final static long ONE_SECOND = 1000L;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(@RequestBody OrderRO orderRO) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            log.info("[{}] We're preparing the {} for {}... {}%", orderRO.getOrderId(), orderRO.getMeal(), orderRO.getCustomerName(), (i+1)*10);
            Thread.sleep(ONE_SECOND);
        }

        String response = String.format("The %s for %s is ready!", orderRO.getMeal(), orderRO.getCustomerName());
        log.info("[{}] {}", orderRO.getOrderId(), response);
        return response;
    }
}
