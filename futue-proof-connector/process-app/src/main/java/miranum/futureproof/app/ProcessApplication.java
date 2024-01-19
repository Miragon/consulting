package miranum.futureproof.app;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
@EnableZeebeClient
public class ProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessApplication.class, args);
    }

    @JobWorker(type = "getCustomer_NotificationPreference")
    public Map<String, Object> getCustomer_NotificationPreference() {
        List<String> givenList = Arrays.asList("SMS", "EMAIL");
        Map<String, Object> variables = new HashMap<>();
        Random rand = new Random();
        String randomElement = givenList.get(rand.nextInt(givenList.size()));

        variables.put("notificationMethod", randomElement);
        return variables;
    }
}
