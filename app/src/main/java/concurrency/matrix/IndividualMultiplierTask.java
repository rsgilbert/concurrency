package concurrency.matrix;

/**
 * A {@link Runnable} class for calculating the matrix multiplication value for a particular cell in the result matrix.
 */
public class IndividualMultiplierTask implements Runnable {
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;

    private final int row;
    private final int column;

    public IndividualMultiplierTask(
            double[][] result,
            double[][] matrix1,
            double[][] matrix2,
            int i,
            int j
    ) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = i;
        this.column = j;
    }

    /**
     * The run method calculates the value for the result matrix position determined by the row and column
     * attributes.
     */
    @Override
    public void run() {
        double total = 0;
        int columnCount = matrix1[0].length;
        for(int c = 0; c < columnCount; c++) {
            total += matrix1[row][c] * matrix2[c][column];
        }
        result[row][column] = total;
    }

}
