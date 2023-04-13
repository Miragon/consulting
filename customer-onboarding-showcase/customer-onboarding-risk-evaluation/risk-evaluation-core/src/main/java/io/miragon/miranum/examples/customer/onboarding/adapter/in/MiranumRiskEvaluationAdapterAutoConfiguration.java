package io.miragon.miranum.examples.customer.onboarding.adapter.in;

import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiranumRiskEvaluationAdapterAutoConfiguration{
    @Bean
    public MiranumRiskEvaluationAdapter miranumRiskEvaluationAdapter(final EvaluateRiskUseCase evaluateRiskUseCase) {
        return new MiranumRiskEvaluationAdapter(evaluateRiskUseCase);
    }
}
