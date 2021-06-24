package concurrency.matrix;

import concurrency.TimingExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests that the implementation and running time of multiplying matrices row by row is working fine
 */
@ExtendWith(TimingExtension.class)
public class ParallelRowMultiplierTest {


    double[][] matrix1 = { {1, 2, 3}, {4, 5, 6} }; // 2 X 3
    double[][] matrix2 = { {5, 10}, {10, 15}, {20, 25} }; // 3 X 2
    double[][] result = new double[2][2];

    // cell 00
    double cell00 = matrix1[0][0] * matrix2[0][0] + // 1*5 + 2*10 + 3*20
            matrix1[0][1] * matrix2[1][0] +
            matrix1[0][2] * matrix2[2][0];

    // cell 01
    double cell01 = matrix1[0][0] * matrix2[0][1] + // 1*10 + 2*15 + 3*25
            matrix1[0][1] * matrix2[1][1] +
            matrix1[0][2] * matrix2[2][1];

    // cell 10
    double cell10 = matrix1[1][0] * matrix2[0][0] + // 4*5 + 5*10 + 6*20
            matrix1[1][1] * matrix2[1][0] +
            matrix1[1][2] * matrix2[2][0];

    // cell 01
    double cell11 = matrix1[1][0] * matrix2[0][1] + // 4*10 + 5*15 + 6*25
            matrix1[1][1] * matrix2[1][1] +
            matrix1[1][2] * matrix2[2][1];


    @BeforeEach
    void setup() {
        result = new double[2][2];
    }

    @DisplayName("parallel row multiplier gives correct result")
    @Test
    void parallelRowMultiplierIsCorrect() {
        ParallelRowMultiplierTask.multiply(matrix1, matrix2, result);
        assertThat(result[0][0]).isEqualTo(cell00);
        assertThat(result[0][1]).isEqualTo(cell01);
        assertThat(result[1][0]).isEqualTo(cell10);
        assertThat(result[1][1]).isEqualTo(cell11);
    }

    @DisplayName("parallel row multiplier running time test")
    @Test // Takes appr 2500 ms
    void parallelRowMultiplierRunningTime() {
        matrix1 = MatrixGenerator.generate(100, 200); // 100 x 200
        matrix2 = MatrixGenerator.generate(200, 500); // 200 x 500
        result = new double[100][500];
        ParallelRowMultiplierTask.multiply(matrix1, matrix2, result);
    }

    @DisplayName("serial multiplier running time test")
    @Test // Takes appr 50 ms
    void serialMultiplierRunningTime() {
        matrix1 = MatrixGenerator.generate(100, 200); // 100 x 200
        matrix2 = MatrixGenerator.generate(200, 500); // 200 x 500
        result = new double[100][500];
        SerialMultiplier.multiply(matrix1, matrix2, result);
    }

    @DisplayName("compare parallel row and serial results")
    @Test
    void compareSerialAndParallelCorrectness() {
        matrix1 = MatrixGenerator.generate(100, 200); // 100 x 200
        matrix2 = MatrixGenerator.generate(200, 500); // 200 x 500
        var serialResult = new double[100][500];
        var parallelResult = new double[100][500];
        SerialMultiplier.multiply(matrix1, matrix2, serialResult);
        ParallelRowMultiplierTask.multiply(matrix1, matrix2, parallelResult);
        for(int r =0; r < serialResult.length; r++) {
            for(int c = 0; c < serialResult[0].length; c++) {
                assertThat(serialResult[r][c]).isEqualTo(parallelResult[r][c]);
            }
        }
    }
}
