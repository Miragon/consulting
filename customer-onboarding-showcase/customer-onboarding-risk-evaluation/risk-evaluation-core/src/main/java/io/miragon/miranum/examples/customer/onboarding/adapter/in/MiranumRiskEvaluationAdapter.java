package io.miragon.miranum.examples.customer.onboarding.adapter.in;

import io.miragon.miranum.connect.elementtemplate.api.BPMNElementType;
import io.miragon.miranum.connect.elementtemplate.api.ElementTemplate;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskUseCase;
import io.miragon.miranum.examples.customer.onboarding.domain.Risk;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MiranumRiskEvaluationAdapter {

    private final EvaluateRiskUseCase evaluateRiskUseCase;

    @Worker(type = "evaluateRisk")
    @ElementTemplate(name = "Evaluate Risk", description = "Evaluate the risk of a customer.")
    public Risk evaluateRisk(EvaluateRiskCommand evaluateRiskCommand) {
        return evaluateRiskUseCase.evaluateRisk(evaluateRiskCommand);
    }
}