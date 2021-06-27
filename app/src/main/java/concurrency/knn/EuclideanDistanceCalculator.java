package concurrency.knn;

/**
 * Auxiliary class for calculating the distance between two samples.
 */
public class EuclideanDistanceCalculator {
    /**
     * Calculates the distance between two {@link Sample}s
     * @param sample1 sample 1
     * @param sample2 sample 2
     * @return distance between sample1 and sample2
     */
    public static double calculate(Sample sample1, Sample sample2) {
        double[] data1 = sample1.getExample();
        double[] data2 = sample2.getExample();

        if(data1.length != data2.length)
            throw new IllegalArgumentException("Given vectors do not have the same length");

        double result = 0;
        for(int i = 0; i < data1.length; i++) {
            result += Math.pow(data1[i] - data2[i], 2);
        }
        return Math.sqrt(result);
    }
}
