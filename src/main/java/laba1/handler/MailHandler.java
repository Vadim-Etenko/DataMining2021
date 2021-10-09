package laba1.handler;

import laba1.csv_object.Mail;
import java.util.List;

public interface MailHandler {

    List<Mail> process(List<Mail> mailList);

}
