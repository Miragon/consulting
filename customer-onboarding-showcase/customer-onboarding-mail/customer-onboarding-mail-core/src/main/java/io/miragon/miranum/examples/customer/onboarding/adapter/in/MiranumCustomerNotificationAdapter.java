package io.miragon.miranum.examples.customer.onboarding.adapter.in;

import io.miragon.miranum.connect.elementtemplate.api.BPMNElementType;
import io.miragon.miranum.connect.elementtemplate.api.GenerateElementTemplate;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailCommand;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MiranumCustomerNotificationAdapter {

    private final SendMailUseCase sendMailUseCase;

    @Worker(type = "mailDelivery")
    @GenerateElementTemplate(name = "Mail Delivery",
            id = "mail-delivery",
            type = "mailDelivery",
            appliesTo = {BPMNElementType.BPMN_SERVICE_TASK},
            version = 1)
    public void notifyCustomer(MailCommand sendMailCommand) {
        SendMailCommand mailCommand = SendMailCommandFactory.create(sendMailCommand);
        sendMailUseCase.sendMail(mailCommand);
    }
}
