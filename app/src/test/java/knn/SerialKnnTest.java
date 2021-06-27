package knn;

import concurrency.TimingExtension;
import concurrency.knn.KnnClassifier;
import concurrency.knn.Sample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
        var s2Close = new Sample(new double[] {2.8, 3.9, 2.1, 3}, "s2Close");
        String s1Match = knn.classify(s1Close);
        String s2Match = knn.classify(s2Close);
        System.out.println(s1Match);
        System.out.println(s2Match);
        assertThat(s1Match).isEqualTo(s1.getTag());
        assertThat(s2Match).isEqualTo(s2.getTag());
    }



}
