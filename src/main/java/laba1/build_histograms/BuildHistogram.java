package laba1.build_histograms;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BuildHistogram {

    public static void build(List<Integer> doubleList, String fileName,
                             String title, String xLabel, String yLabel) throws IOException {

        double[] pointsArray = new double[doubleList.size()] ;
        for (int i = 0; i < doubleList.size(); i++) {
            pointsArray[i] = (double) doubleList.get(i);
        }

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", pointsArray, 35);

        JFreeChart chart = ChartFactory.
                createXYAreaChart(title,
                        xLabel,
                        yLabel,
                        dataset);

        ChartUtils.saveChartAsPNG(new File(fileName), chart, 700, 400);
        System.out.println("Done");
    }

    protected static double findArithmeticMean(List<Integer> mean) {
        double sum = 0;
        for(int i : mean) {
            sum += i;
        }
        return sum / mean.size();
    }
}
