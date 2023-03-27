package io.miragon.miranum.examples.customer.onboarding.application.service;

import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskCommand;
import io.miragon.miranum.examples.customer.onboarding.application.port.in.EvaluateRiskUseCase;
import io.miragon.miranum.examples.customer.onboarding.domain.Customer;
import io.miragon.miranum.examples.customer.onboarding.domain.Evaluation;
import io.miragon.miranum.examples.customer.onboarding.domain.Risk;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.logging.Logger;

@AllArgsConstructor
@Log
public class RiskEvaluationService implements EvaluateRiskUseCase {
    private final Evaluation evaluation;

    @Override
    public Risk evaluateRisk(EvaluateRiskCommand evaluateRiskCommand) {

        Customer customer = new Customer(
                evaluateRiskCommand.getName(),
                evaluateRiskCommand.getAddress(),
                evaluateRiskCommand.getEmail(),
                evaluateRiskCommand.getEmployment(),
                Double.parseDouble(evaluateRiskCommand.getIncome()));

        Risk riskLevel= evaluation.makeRiskEvaluation(customer);
        log.info("Risk level for customer " + customer.getName() + " is " + riskLevel.getRiskLevel());
        return riskLevel;
    }
}
