package main;

import build_histograms.BuildHistogramSentenceLength;
import build_histograms.BuildHistogramWordLength;
import build_histograms.BuildHistogramWordsCounter;
import csv_object.Mail;
import handler.MainHandler;
import parsers.ParserCsvToList;
import parsers.SaveCollectionToTxt;
import words_counter.WordsCounter;

import java.util.List;
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

    }
}
