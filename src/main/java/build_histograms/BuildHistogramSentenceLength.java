package build_histograms;

import csv_object.Mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildHistogramSentenceLength extends BuildHistogram {

    public static void build(List<Mail> mailList, String fileName) throws IOException {
        List<Integer> sentenceLength = getSentenceLengthList(mailList);
        String title = "������� ������ (������� ������� - " + findArithmeticMean(sentenceLength) + ")";
        build(sentenceLength, fileName,
                title, "������� �������", "ʳ������ ��������");
    }

    private static List<Integer> getSentenceLengthList(List<Mail> mailList) {
        List<Integer> sentenceLengthList = new ArrayList<>();
        for (Mail mail : mailList) {
            for (String s : mail.getContent().split(System.lineSeparator())) {
                if (s.length() != 0) {
                    sentenceLengthList.add(s.length());
                }
            }
        }
        return sentenceLengthList;
    }

}
