package io.miragon.miranum.examples.customer.onboarding.adapter.in;

import io.miragon.miranum.connect.elementtemplate.api.BPMNElementType;
import io.miragon.miranum.connect.elementtemplate.api.ElementTemplate;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailCommand;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MiranumCustomerNotificationAdapter {

    private final SendMailUseCase sendMailUseCase;

    @Worker(type = "mailDelivery")
    @ElementTemplate(name = "Mail Delivery", description = "Send a mail to a customer.")
    public void notifyCustomer(MailCommand sendMailCommand) {
        SendMailCommand mailCommand = SendMailCommandFactory.create(sendMailCommand);
        sendMailUseCase.sendMail(mailCommand);
    }
}
