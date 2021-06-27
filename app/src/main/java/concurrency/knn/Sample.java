package concurrency.knn;

public class Sample {
    private final double[] example;
    private final String tag;

    public Sample(double[] example, String tag) {
        this.example = example;
        this.tag = tag;
    }

    public double[] getExample() {
        return example;
    }

    public String getTag() {
        return tag;
    }
}
