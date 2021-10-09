package laba2.counter;

import laba1.csv_object.Mail;
import laba1.words_counter.WordsCounter;
import laba2.txt_reader.TxtReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordCounterLaba2 {

    public static final String SPACE = " ";
    public List<Mail> spamMail;
    public List<Mail> hamMail;

    public WordCounterLaba2(List<Mail> spamMail, List<Mail> hamMail) {
        this.spamMail = spamMail;
        this.hamMail = hamMail;
    }

    public void handle(int type, String handle) {
        TxtReader reader = new TxtReader();
        List<Mail> listWords;

        if(type == 1) {
            listWords = spamMail;
        } else {
            listWords = hamMail;
        }

        ArrayList<String> allText = reader.getAllWords();
        WordsCounter wordsCounter = new WordsCounter();
        HashMap<String, Integer> wordMap = wordsCounter.process(listWords);

        if (!wordMap.containsKey(handle)) {
            System.out.println("Нет такого сообщение в данных");
            return;
        }

        double i1 = (float) wordMap.get(handle) / listWords.size(); // P(Type)
        double i2 = (float) listWords.size() / allText.size(); // P(Text | Type)
        double i3 = (float) wordsCounter.proces(allText).get(handle) / allText.size(); // P(Text)

        System.out.println("Ответ - " + (i1 * i2 / i3));

    }

    public HashMap<String, Integer> getCount(List<String> list) {
        HashMap<String, Integer> wordsCountMap = new HashMap<>();
        for (String sTemp : list) {
            for (String s : sTemp.split(SPACE)) {
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
