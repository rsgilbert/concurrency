package concurrency.matrix;

/**
 * Gets result of multiplying two matrices only considering a single row
 * Multiplies a single row in matrix1 with a all columns in matrix2
 */
public class RowMultiplierTask implements Runnable {

    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int matrix1RowIdx;
    /**
     * Constructs the RowMultiplierTask initializing instance variables that will be used by run()
     * @param result the final result matrix
     * @param matrix1 the matrix to pick the row from
     * @param matrix2 the matrix to pick the column from
     * @param matrix1RowIdx identifies the row in matrix1 to pick
     */
    public RowMultiplierTask(
            double[][] result,
            double[][] matrix1,
            double[][] matrix2,
            int matrix1RowIdx
    ) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.matrix1RowIdx = matrix1RowIdx;
    }


    /**
     * Method responsible for multiplying a single row in matrix1 with
     * all the columns in matrix 2.
     * <p>
     * Once this method completes, the matrix1RowIdx row in result will have all its cells populated.
     */
    @Override
    public void run() {
        int matrix2ColumnCount = matrix2[0].length;
        for(int matrix2ColumnIdx = 0; matrix2ColumnIdx < matrix2ColumnCount; matrix2ColumnIdx++) {
            result[matrix1RowIdx][matrix2ColumnIdx] = 0;
            int matrix1ColumnCount = matrix1[0].length;
            for (int c = 0; c < matrix1ColumnCount; c++) {
                result[matrix1RowIdx][matrix2ColumnIdx] += matrix1[matrix1RowIdx][c] * matrix2[c][matrix2ColumnIdx];
            }
        }
    }
}
