package applicant.employee.application.application.port.out.formFeedback;

public interface FormFeedbackOutPort {
    void propagateFeedback(FormFeedbackOutCommand formFeedbackOutCommand);
}
