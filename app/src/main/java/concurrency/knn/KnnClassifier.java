package concurrency.knn;

import java.util.*;

/**
 * Serial version of the k-nearest neighbors algorithm.
 * Stores the train dataset and the number k. k is the number of examples that we will use to determine the
 * tag of an instance.
 * @see <a href="https://towardsdatascience.com/machine-learning-basics-with-the-k-nearest-neighbors-algorithm-6a6e71d01761">KNN Algorithm</a>
 */
public class KnnClassifier {

    /**
     * A list of samples to use when classifying
     */
    private final List<? extends Sample> dataset;
    /**
     * The k in K-nearest neighbors.
     * <p>Number of examples that we will use to determine the tag of an instance</p>
     */
    private int k;

    public KnnClassifier(List<? extends Sample> dataset, int k) {
        this.dataset = dataset;
        this.k = k;
    }

    /**
     * Receives a {@link Sample} object with the instance we want to classify
     * and returns a string with the tag assigned to that instance.
     * @return tag assigned to instance in Sample
     */
    public String classify(Sample example) {
        // Calculate the distances between the input example and
        // all the examples of the train dataset
        Distance[] distances = new Distance[dataset.size()];
        int index = 0;
        for(Sample localExample : dataset) {
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
            index ++;
        }
        // Sort the examples from the lower to the higher distance
        Arrays.sort(distances);
        // We count the tag with most instances in the k-nearest examples
        // In other words we get the mode
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Sample localExample = dataset.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, Integer::sum);
        }
        // return the tag that appears the most
        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}

