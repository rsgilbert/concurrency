package knn;

import concurrency.TimingExtension;
import concurrency.knn.KnnClassifier;
import concurrency.knn.Sample;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(TimingExtension.class)
public class SerialKnnTest {

    class Pot implements Comparable<Pot> {
        private final int count;
        public Pot(int count) { this.count = count; }
        @Override
        public int compareTo(Pot p) {
            return count - p.count;
        }
    }

    @Test
    void potComp() {
        Pot p1 = new Pot(3);
        Pot p2 = new Pot(1);
        Pot p3 = new Pot(5);
        Pot[] ps = { p1, p2, p3 };
        Arrays.sort(ps);
        for(Pot p : ps) System.out.println(p.count);
    }

    @Test
    void classify_isCorrect() {
        Sample s1 = new Sample(new double[]{1.1, 1.2, 1.3, 1.4}, "green");
        Sample s2 = new Sample(new double[]{2.1, 2.2, 2.3, 2.4}, "red");
        Sample s3 = new Sample(new double[]{3.1, 3.2, 3.3, 3.4}, "green");
        Sample s4 = new Sample(new double[]{4.1, 4.2, 4.3, 4.4}, "red");
        List<Sample> dataset = Arrays.asList(s1, s2, s3, s4);
        KnnClassifier knn = new KnnClassifier(dataset, 3);
        var s1Close = new Sample(new double[]{1.3, 1.9, 0.2, 1}, "s1Close");
        var s2Close = new Sample(new double[]{2.8, 3.9, 2.1, 3}, "s2Close");
        String s1Match = knn.classify(s1Close);
        String s2Match = knn.classify(s2Close);
        System.out.println(s1Match);
        System.out.println(s2Match);
        assertThat(s1Match).isEqualTo(s1.getTag());
        assertThat(s2Match).isEqualTo(s2.getTag());
    }

    @Test
    void classify_usingDataFromCSV() {
        List<Sample> dataset = getDatasetFromCSV();
        KnnClassifier knn = new KnnClassifier(dataset, 10);
        var s1 = new Sample(new double[] { 30, 300, 5, -1, 90, -30, 0.5, 4000 }, "admin.");
        // Lets find out his likely job
        String knnMatch1 = knn.classify(s1);
        assertThat(knnMatch1).isEqualTo(s1.getTag());
        System.out.println(knnMatch1); // gives admin.
    }


    /**
     * Read knn test data from a resource csv file
     * and return the data as a list of sample
     * @return list of samples denoting the data extracted from the csv file
     * @see <a href="https://javadoc.io/doc/org.apache.commons/commons-csv/latest/index.html">Apache commons CSV parser</a>
     */
    public static List<Sample> getDatasetFromCSV() {
        List<Sample> samples = new ArrayList<>();
        try {
            String fileName = "age-balance-job.csv";
            File csvFile = new File(ClassLoader.getSystemResource(fileName).toURI());
            CSVParser parser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.DEFAULT);
            for(CSVRecord r : parser.getRecords()) {
                if(r.getRecordNumber() == 1)
                    // Skip header record
                    continue;
                double age          = Double.parseDouble(r.get(0));
                double duration     = Double.parseDouble(r.get(1));
                double campaign     = Double.parseDouble(r.get(2));
                double empVarRate   = Double.parseDouble(r.get(3));
                double consPriceIdx = Double.parseDouble(r.get(4));
                double consConfIdx  = Double.parseDouble(r.get(5));
                double eurobor3m    = Double.parseDouble(r.get(6));
                double nrEmployed   = Double.parseDouble(r.get(7));
                String job          = r.get(8);
                double[] example = { age, duration, campaign, empVarRate, consPriceIdx, consConfIdx, eurobor3m, nrEmployed };
                Sample sample = new Sample(example, job);
                samples.add(sample);
            }
            return samples;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error reading resource file");
        }
    }



}
