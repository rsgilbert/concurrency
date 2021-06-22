package concurrency.matrix;

public class Matrix {
    public static void logMatrix(double[][] matrix) {
        for(var col: matrix) {
            for(var cell: col) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
