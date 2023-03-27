package io.miragon.miranum.examples.customer.onboarding.application.port.in;

import io.miragon.miranum.examples.customer.onboarding.domain.Risk;

public interface EvaluateRiskUseCase {
    Risk evaluateRisk(EvaluateRiskCommand evaluateRiskCommand);
}
