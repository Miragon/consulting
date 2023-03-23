package io.miragon.miranum.examples.com.adapter.com.adapter.in;

import io.miragon.miranum.examples.com.adapter.in.MailCommand;
import io.miragon.miranum.integrations.mail.application.port.in.SendMailCommand;

public class SendMailCommandFactory {

    public static SendMailCommand create(MailCommand mailCommand) {
        final String recipient = mailCommand.getEmail();
        final String subject = "Your application status";
        final String text = generateText(mailCommand);
        return new SendMailCommand(
                recipient,
                subject,
                text
        );
    }

    private static String generateText(MailCommand mailCommand) {
        return String.format(
                "Hi %s, \n" +
                        "Thank you for your interest! \n" +
                        "Your application: \n %s %s!",
                mailCommand.getName(),
                mailCommand.getContent()
        );
    }
}