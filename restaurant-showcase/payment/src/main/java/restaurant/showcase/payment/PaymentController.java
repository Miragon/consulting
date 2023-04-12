package restaurant.showcase.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController {

    private final static long ONE_SECOND = 1000L;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String payment(@RequestBody OrderRO orderRO) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            log.info("{} We're processing the payment for {}'s {}... {}%", orderRO.getOrderId(), orderRO.getCustomerName(), orderRO.getMeal(), (i+1)*20);
            Thread.sleep(ONE_SECOND);
        }

        String response = String.format("Payment for %s's %s is done!", orderRO.getCustomerName(), orderRO.getMeal());
        log.info("{} {}", orderRO.getOrderId(), response);
        return response;
    }
}
