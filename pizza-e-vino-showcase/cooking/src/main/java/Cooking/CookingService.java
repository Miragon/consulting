package Cooking;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CookingService {

    long waitTimeMillis = 1000;
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String RESET = "\033[0m";  // Text Reset

    @RequestMapping(value = "/WorkIt", method = RequestMethod.PUT)
    public String index(@RequestBody OrderMessageRequest orderRequest) throws InterruptedException {
        int loops = 10;
        System.out.println();
        System.out.println("We're Working on it! -- "+ orderRequest.orderMessage);
        while(loops >= 0) {
            System.out.print(GREEN_BACKGROUND_BRIGHT + ".!." + RESET);
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        System.out.println();
        System.out.println("We've done it! -- "+ orderRequest.orderMessage);

        return "You have said "+ orderRequest.orderMessage + "";
    }
}
