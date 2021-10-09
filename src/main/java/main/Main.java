package main;

import laba1.build_histograms.BuildHistogramSentenceLength;
import laba1.build_histograms.BuildHistogramWordLength;
import laba1.build_histograms.BuildHistogramWordsCounter;
import laba1.csv_object.Mail;
import laba1.handler.MainHandler;
import laba1.parsers.ParserCsvToList;
import laba1.parsers.SaveCollectionToTxt;
import laba1.words_counter.WordsCounter;
import laba2.counter.WordCounterLaba2;
import laba2.txt_reader.TxtReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final String FILE_PATH = "src/main/resources/sms-spam-corpus.csv";
    private static final String OUTPUT_DIRECTORY = "output/";
    private static final String TYPE_HAM = "ham";
    private static final String TYPE_SPAM = "spam";
    private static final char SEPARATOR = ',';
    private static final char QUOT_CHAR = '\n';

    public static void main(String[] args) throws Exception {
        List<Mail> allMailsList = ParserCsvToList.read(FILE_PATH, SEPARATOR, QUOT_CHAR);

        List<Mail> hamMail = allMailsList
                .stream()
                .filter(m -> m.getType().equals(TYPE_HAM))
                .collect(Collectors.toList());

        List<Mail> spamMail = allMailsList
                .stream()
                .filter(m -> m.getType().equals(TYPE_SPAM))
                .collect(Collectors.toList());

        spamMail = new MainHandler().process(spamMail);
        hamMail = new MainHandler().process(hamMail);

        SaveCollectionToTxt.write(spamMail, OUTPUT_DIRECTORY + "sms-spam-corpus.txt");
        SaveCollectionToTxt.write(hamMail, OUTPUT_DIRECTORY + "sms-ham-corpus.txt");

        SaveCollectionToTxt.write(new WordsCounter().process(spamMail),
                OUTPUT_DIRECTORY + "spamWords.txt");

        SaveCollectionToTxt.write(new WordsCounter().process(hamMail),
                OUTPUT_DIRECTORY + "hamWords.txt");

        BuildHistogramWordLength.build(spamMail, OUTPUT_DIRECTORY + "spamMailLengthWord.PNG");
        BuildHistogramWordLength.build(hamMail, OUTPUT_DIRECTORY + "hamMailLengthWord.PNG");

        BuildHistogramSentenceLength.build(spamMail, OUTPUT_DIRECTORY + "spamMailLengthSentence.PNG");
        BuildHistogramSentenceLength.build(hamMail, OUTPUT_DIRECTORY + "hamMailLengthSentence.PNG");

        BuildHistogramWordsCounter.build(spamMail, OUTPUT_DIRECTORY + "spamMostFrequentWords.PNG");
        BuildHistogramWordsCounter.build(hamMail, OUTPUT_DIRECTORY + "hamMostFrequentWords.PNG");


        System.out.println("¬ведите файл дл€ поиска" + System.lineSeparator() + "1 - spam" + System.lineSeparator() + "2 - ham");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();

        System.out.println("¬ведите сообщение дл€ поиска");
        String message = scanner.next();

        new WordCounterLaba2(spamMail, hamMail).handle(type,message);
    }

}
