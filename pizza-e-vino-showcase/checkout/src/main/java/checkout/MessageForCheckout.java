package checkout;

import checkout.websockets.NotificationApplicationListener;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageForCheckout {
	private final NotificationApplicationListener notificationApp;

    @RequestMapping(value = "/messageForCheckout", method = RequestMethod.PUT)
    public String message(@RequestBody OrderRO orderMessageRequest) {

        if (orderMessageRequest.getPizza().contains("OUT OF STOCK") || orderMessageRequest.getPizza().contains("No Pineapple")) {

            notificationApp.notify("<p style=\"border:2px solid Red;\"> -- " + orderMessageRequest.getPizza() + " " +
                    "for" +
                    " " + orderMessageRequest.getCustomerName() + " --  </p>");

        } else if (orderMessageRequest.getPizza().contains("order is taking a little longer")) {
            notificationApp.notify("<p style=\"border:2px solid Yellow;\"> -- " + orderMessageRequest.getPizza() +
                    " " +
                    "for" +
                    " " + orderMessageRequest.getCustomerName() + " --  </p>");
        } else {
            notificationApp.notify("<p style=\"border:2px solid Green;\"> -- " + orderMessageRequest.getCustomerName() + " for" +
                    " " + orderMessageRequest.getCustomerName() + " --  </p>");
        }
        return "success";
    }
}
