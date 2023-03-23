package io.miragon.miranum.examples.com.adapter.in;

import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailCommand;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MiranumCustomerNotificationAdapter {

    private final SendMailUseCase sendMailUseCase;

    @Worker(type = "sendMail")
    public void notifyCustomer(MailCommand sendMailCommand) {
        SendMailCommand mailCommand = io.miragon.miranum.examples.com.adapter.com.adapter.in.SendMailCommandFactory.create(sendMailCommand);
        // Simulate delay
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sendMailUseCase.sendMail(mailCommand);
    }
}
