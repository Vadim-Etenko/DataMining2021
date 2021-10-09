package laba1.handler;

import laba1.csv_object.Mail;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ToLowerCaseHandler implements MailHandler {

    @Override
    public List<Mail> process(List<Mail> mailList) {
        return mailList
                .stream()
                .map(mail -> new Mail(mail.getType(), mail.getContent().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

}
