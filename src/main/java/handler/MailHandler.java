package handler;

import csv_object.Mail;
import java.util.List;

public interface MailHandler {

    List<Mail> process(List<Mail> mailList);

}
