package io.miragon.miranum.examples.customer.onboarding.adapter.in;

import io.miragon.miranum.connect.elementtemplate.api.BPMNElementType;
import io.miragon.miranum.connect.elementtemplate.api.GenerateElementTemplate;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskUseCase;
import io.miragon.miranum.examples.customer.onboarding.domain.Risk;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MiranumRiskEvaluationAdapter {

    private final EvaluateRiskUseCase evaluateRiskUseCase;

    @Worker(type = "evaluateRisk")
    @GenerateElementTemplate(name = "Evaluate Risk",
            id = "evaluate-risk",
            type = "evaluateRisk",
            appliesTo = {BPMNElementType.BPMN_SERVICE_TASK},
            version = "1")
    public Risk evaluateRisk(EvaluateRiskCommand evaluateRiskCommand) {
        return evaluateRiskUseCase.evaluateRisk(evaluateRiskCommand);
    }
}