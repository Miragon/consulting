package restaurant.showcase.kitchen;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CookingService {

    private final static long ONE_SECOND = 1000L;

    @RequestMapping(value = "/pizza/bake", method = RequestMethod.POST)
    public String index(@RequestBody OrderRO orderRequest) throws InterruptedException {
        System.out.printf("We're working the %s for %s!", orderRequest.getCustomerName());
        for (int i = 0; i < 10; i++) {
            System.out.print("..");
            Thread.sleep(ONE_SECOND);
        }

        return String.format("Pizza for %s is ready!", orderRequest.getCustomerName());
    }
}
