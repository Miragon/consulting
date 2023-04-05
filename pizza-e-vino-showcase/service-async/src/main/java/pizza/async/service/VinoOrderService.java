package pizza.async.service;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.springframework.stereotype.Component;

@Component
public class VinoOrderService implements OrderService {
    private final static long ONE_SECOND = 1000L;

    @JobWorker(type = "sortVinoOrder", fetchAllVariables = true)
    public void execute(@Variable String customerName, @Variable String orderMessage) throws Exception {
        System.out.print("We're working on getting your Vino! ");
        for (int i = 0; i < 3; i++) {
            System.out.print("..");
            Thread.sleep(ONE_SECOND);
        }
        System.out.printf("%nVino for %s is up!%n", customerName);
    }
}
