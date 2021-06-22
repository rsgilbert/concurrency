package concurrency.matrix;

public class SerialMultiplier {

    /**
     * Multiplies two matrices matrix1 and matrix2 and stores the result
     * in another matrix result
     */
    public static void multiply(double[][] matrix1,
                                double[][] matrix2,
                                double[][] result) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int columns2 = matrix2[0].length;

        // Iterate over rows in matrix1
        for(int r1 = 0; r1 < rows1; r1++) {
            // Iterate over columns in matrix2
            for(int c2 = 0; c2 < columns2; c2++) {
                double total = 0;
                // Note that number of columns in matrix1 is equal to number of rows in matrix2
                for (int c1 = 0; c1 < columns1; c1++) {
                    int r2 = c1;
                    total += matrix1[r1][c1] * matrix2[r2][c2];
                }
                result[r1][c2] = total;
            }
        }
    }
}
