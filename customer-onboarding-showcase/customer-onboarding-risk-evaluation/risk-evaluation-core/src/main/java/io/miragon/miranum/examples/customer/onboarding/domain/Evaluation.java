package io.miragon.miranum.examples.customer.onboarding.domain;

public class Evaluation {
    public Risk makeRiskEvaluation(Customer customer) {
        if(customer.getIncome() > 10000) {
            return new Risk(0);
        } else if(customer.getIncome() > 5000) {
            return new Risk(1);
        } else {
            return new Risk(2);
        }
    }
}
