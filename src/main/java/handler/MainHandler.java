package handler;

import csv_object.Mail;

import java.util.List;

public class MainHandler implements MailHandler{

    @Override
    public List<Mail> process(List<Mail> mailList) {
        mailList = new ToLowerCaseHandler().process(mailList);
        mailList = new DeleteNotNumbersHandler().process(mailList);
        return new DeleteStopWordsHandler().process(mailList);
    }
}
