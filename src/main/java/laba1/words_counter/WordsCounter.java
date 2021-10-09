package laba1.words_counter;

import laba1.csv_object.Mail;

import java.util.HashMap;
import java.util.List;

/**
 * @author Artem Malakhov
 */

public class WordsCounter {

    private static final String SPACE = " ";

    public HashMap<String, Integer> process(List<Mail> mailList) {
        HashMap<String, Integer> wordsCountMap = new HashMap<>();
        for (Mail mail : mailList) {
            for (String s : mail.getContent().split(SPACE)) {
                if (wordsCountMap.containsKey(s) && !s.equals("") && !s.equals(" ")) {
                    wordsCountMap.put(s, wordsCountMap.get(s) + 1);
                } else {
                    wordsCountMap.put(s, 1);
                }
            }
        }
        return wordsCountMap;
    }

    public HashMap<String, Integer> proces(List<String> mailList) {
        HashMap<String, Integer> wordsCountMap = new HashMap<>();
        for (String s1 : mailList) {
            for (String s : s1.split(SPACE)) {
                if (wordsCountMap.containsKey(s) && !s.equals("") && !s.equals(" ")) {
                    wordsCountMap.put(s, wordsCountMap.get(s) + 1);
                } else {
                    wordsCountMap.put(s, 1);
                }
            }
        }
        return wordsCountMap;
    }

}
