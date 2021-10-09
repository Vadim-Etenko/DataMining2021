package laba1.handler;

import laba1.csv_object.Mail;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteNotNumbersHandler implements MailHandler {

    String regex = "[^a-zA-Z^ \\t\\n\\r]";

    @Override
    public List<Mail> process(List<Mail> mailList) {
        return mailList
                .stream()
                .map(mail -> new Mail(mail.getType(), mail.getContent().replaceAll(regex, "")))
                .collect(Collectors.toList());

    }
}
