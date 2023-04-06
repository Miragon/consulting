package restaurant.showcase.waiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WaiterController {

    @RequestMapping(value = "/calm/customer", method = RequestMethod.POST)
    public void payment(@RequestBody OrderRO orderRO) throws InterruptedException {
        log.info("Jo {}, calm down! Your order is on its way!", orderRO.getCustomerName());
    }
}
