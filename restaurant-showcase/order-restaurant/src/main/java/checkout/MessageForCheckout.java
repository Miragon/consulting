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
        return "success";
    }
}
