package concurrency.matrix;

import java.util.Random;

public class MatrixGenerator {

    /**
     * Receives the number of rows and columns you want in your matrix as parameters and
     * generates a matrix with those dimensions with random double numbers
     * @param rows number of rows
     * @param cols number of columns
     * @return a matrix with given rows and columns
     */
    public static double[][] generate(int rows, int cols) {
        double[][] ret = new double[rows][cols];
        Random random = new Random();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                ret[i][j] = (int) (random.nextInt() * 10);
            }
        }
        return ret;
    }

}
