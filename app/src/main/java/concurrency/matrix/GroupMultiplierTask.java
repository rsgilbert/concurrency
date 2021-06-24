package concurrency.matrix;

/**
 * Multiplies a group of rows in matrix1 with a group of columns in matrix2 and stores the result in a group of rows in result.
 * If we have more processors than cores, a more efficient implementation than this is possible by spitting the row into multiple sections.
 * The rows to consider are from index rowStartIdx to index rowEndIdx-1
 */
public class GroupMultiplierTask implements Runnable {
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int rowStartIdx;
    private final int rowEndIdx;

    public GroupMultiplierTask(
            double[][] result,
            double[][] matrix1,
            double[][] matrix2,
            int rowStartIdx,
            int rowEndIdx
    ) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.rowStartIdx = rowStartIdx;
        this.rowEndIdx = rowEndIdx;
    }

    /**
     * Performs the multiplication of rows by columns
     */
    @Override
    public void run() {
        int matrix1RowCount = matrix1.length;
        assert rowEndIdx <= matrix1RowCount;

        int matrix1ColumnCount = matrix1[0].length;
        int matrix2RowCount = matrix2.length;
        int matrix2ColumnCount = matrix2[0].length;

        // In a matrix the number of columns in matrix1 must equal to the number of rows in matrix2
        assert matrix1ColumnCount == matrix2RowCount;

        // For each row in matrix1 within the range
        for(int r = rowStartIdx; r < rowEndIdx; r++) {
            // For each column in matrix2
            for(int c = 0; c < matrix2ColumnCount; c++) {
                // Calculate cell value
                for(int i = 0; i < matrix1ColumnCount; i++) {
                    result[r][c] += matrix1[r][i] * matrix2[i][c];
                }
            }
        }
    }


}
