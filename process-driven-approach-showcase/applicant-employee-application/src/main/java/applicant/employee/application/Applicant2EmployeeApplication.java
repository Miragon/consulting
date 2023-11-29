package applicant.employee.application;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath*:**/*.bpmn")
public class Applicant2EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(Applicant2EmployeeApplication.class, args);
    }
}


