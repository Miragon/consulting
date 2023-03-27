package io.miragon.miranum.examples.customer.onboarding;

import io.miragon.miranum.examples.customer.onboarding.adapter.in.MiranumRiskEvaluationAdapterAutoConfiguration;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskUseCase;
import io.miragon.miranum.examples.customer.onboarding.application.service.RiskEvaluationService;
import io.miragon.miranum.examples.customer.onboarding.domain.Evaluation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumRiskEvaluationAdapterAutoConfiguration.class,
})
public class RiskEvaluationAutoConfiguration {
    @Bean
    public Evaluation evaluation() {
        return new Evaluation();
    }

    @Bean
    public EvaluateRiskUseCase evaluateRiskUseCase(final Evaluation evaluation) {
        return new RiskEvaluationService(evaluation);
    }
}
