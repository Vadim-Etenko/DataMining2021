package laba1.build_histograms;

import laba1.csv_object.Mail;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import laba1.words_counter.WordsCounter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildHistogramWordsCounter extends BuildHistogram {

    public static void build(List<Mail> mailList, String fileName) throws IOException {
        HashMap<String, Integer> wordsCountMap = getWordsCountMap(mailList);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double sum = findSum(wordsCountMap);

        for (int i = 0; i < 20; i++) {
            Map.Entry<String, Integer> entry =
                    Collections.max(wordsCountMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue));
            wordsCountMap.remove(entry.getKey());

            dataset.addValue((double) entry.getValue() / sum , "", entry.getKey());

        }

        JFreeChart chart = ChartFactory.
                createBarChart("Найчастіше зустрічаємі слова",
                        "Слова",
                        "Частота зустрічей",
                        dataset);

        ChartUtils.saveChartAsPNG(new File(fileName), chart, 1200, 400);
        System.out.println("Done");

    }


    private static HashMap<String, Integer> getWordsCountMap(List<Mail> mailList) {
        return new WordsCounter().process(mailList);
    }

    protected static double findSum(HashMap<String, Integer> countHashMap) {
        double sum = 0;
        for(int i : countHashMap.values()) {
            sum += i;
        }
        return sum;
    }

}
