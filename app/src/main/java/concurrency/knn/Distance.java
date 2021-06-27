package concurrency.knn;

/**
 * Stores the distance between the Sample input and an instance of the train dataset.
 */
public class Distance implements  Comparable<Distance> {
    int index;
    double distance;

    @Override
    public int compareTo(Distance d) {
        // We multiply by a big number because the difference between the two distances may be very small
        // which will mean that when we round off to integer we shall lose the order
        return (int) ((10_000) * (distance - d.distance));
    }

    public void setIndex(int index) { this.index = index; }

    public int getIndex() {
        return index;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
