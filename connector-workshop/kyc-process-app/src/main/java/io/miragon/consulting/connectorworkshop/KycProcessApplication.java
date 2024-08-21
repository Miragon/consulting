package io.miragon.consulting.connectorworkshop;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import io.miragon.consulting.connectorworkshop.dtos.Application;
import io.miragon.consulting.connectorworkshop.dtos.CheckResult;
import io.miragon.consulting.connectorworkshop.dtos.VerificationResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;


@SpringBootApplication
@EnableZeebeClient
public class KycProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(KycProcessApplication.class, args);
    }

    @JobWorker()
    public CheckResult checkApplicant(@VariablesAsType Application application){
        return new CheckResult(true);
    }

    @JobWorker()
    public VerificationResult verifyApplication(@VariablesAsType Application application){
        return new VerificationResult(true);
    }

    @JobWorker()
    public void startOnboardingProcess(@VariablesAsType Application application){

    }

    @JobWorker()
    public void restCall(@VariablesAsType Application application){
    }
}

