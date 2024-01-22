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
        Map<String, Object> variables = new HashMap<>();
        int pick = new Random().nextInt(NotificationMethod.values().length);
        variables.put("notificationMethod", NotificationMethod.values()[pick]);
        return variables;
    }

    enum NotificationMethod {
        SMS,
        EMAIL
    }
}
