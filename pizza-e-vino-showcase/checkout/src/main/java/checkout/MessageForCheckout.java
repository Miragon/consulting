package checkout;

import checkout.websockets.NotificationApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageForCheckout {
	@Autowired
	NotificationApplicationListener notificationApp;

    @RequestMapping(value = "/messageForCheckout", method = RequestMethod.PUT)
    public String message(@RequestBody OrderRO orderMessageRequest) {

        if (orderMessageRequest.getOrderMessage().contains("OUT OF STOCK") || orderMessageRequest.getOrderMessage().contains("No Pineapple")) {

            notificationApp.notify("<p style=\"border:2px solid Red;\"> -- " + orderMessageRequest.getOrderMessage() + " " +
                    "for" +
                    " " + orderMessageRequest.getCustomerName() + " --  </p>");

        } else if (orderMessageRequest.getOrderMessage().contains("order is taking a little longer")) {
            notificationApp.notify("<p style=\"border:2px solid Yellow;\"> -- " + orderMessageRequest.getOrderMessage() +
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
