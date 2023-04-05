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
    public String message(@RequestBody OrderMessageRequest orderMessageRequest) {

        if (orderMessageRequest.orderMessage.contains("OUT OF STOCK") || orderMessageRequest.orderMessage.contains("No Pineapple")) {

            notificationApp.notify("<p style=\"border:2px solid Red;\"> -- " + orderMessageRequest.orderMessage + " " +
                    "for" +
                    " " + orderMessageRequest.orderName + " --  </p>");

        } else if (orderMessageRequest.orderMessage.contains("order is taking a little longer")) {
            notificationApp.notify("<p style=\"border:2px solid Yellow;\"> -- " + orderMessageRequest.orderMessage +
                    " " +
                    "for" +
                    " " + orderMessageRequest.orderName + " --  </p>");
        } else {
            notificationApp.notify("<p style=\"border:2px solid Green;\"> -- " + orderMessageRequest.orderMessage + " for" +
                    " " + orderMessageRequest.orderName + " --  </p>");
        }
        return "success";
    }
}
