package io.miragon.miranum.examples.customer.onboarding.domain;

public class Evaluation {
    public Risk makeRiskEvaluation(Customer customer) {
        if(customer.getIncome() > 10000) {
            return new Risk("GREEN");
        } else if(customer.getIncome() > 5000) {
            return new Risk("YELLOW");
        } else {
            return new Risk("RED");
        }
    }
}
