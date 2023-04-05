package hello;

import hello.service.OrderMessageRequest;
import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServiceController {
    @Autowired
    private ZeebeClientLifecycle zeebeClient;

    @RequestMapping(value = "/orderUp", method = RequestMethod.POST)
    public String index(@RequestBody OrderMessageRequest orderMessageRequest){

        System.out.println("Got this message for Mike: " + orderMessageRequest.orderMessage);
        Map<String, Object> vars = new HashMap<String, Object>();

        vars.put("orderMessage", orderMessageRequest.orderMessage.toLowerCase());
        vars.put("businessKey", orderMessageRequest.orderName);


        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("ProcessOrder")
                .latestVersion()
                .variables(vars)
                .send();

        return "Your Order is being Taken Care of";
    }
}
