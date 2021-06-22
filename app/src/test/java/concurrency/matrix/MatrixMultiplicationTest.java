package concurrency.matrix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static concurrency.matrix.Matrix.logMatrix;

public class MatrixMultiplicationTest {

    @DisplayName("log generate()")
    @Test
    void logGenerate() {
        var matrix = MatrixGenerator.generate(5, 3);
        logMatrix(matrix);
    }

    @DisplayName("log serial multiply()")
    @Test
    void logMultiply() {
        var matrix1 = MatrixGenerator.generate(2, 1);
        var matrix2 = MatrixGenerator.generate(1, 3);
        System.out.println("Matrix 1");
        logMatrix(matrix1);
        System.out.println("Matrix 2");
        logMatrix(matrix2);
        var result = new double[2][3];
        SerialMultiplier.multiply(matrix1, matrix2, result);
        System.out.println("Result");
        logMatrix(result);
    }



}
