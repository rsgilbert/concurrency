package concurrency.matrix;

import concurrency.TimingExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TimingExtension.class)
public class SerialMultiplyTest {

    double[][] matrix1 = { {1, 2, 3}, {4, 5, 6} }; // 2 X 3
    double[][] matrix2 = { {5, 10}, {10, 15}, {20, 25} }; // 3 X 2
    double[][] result = new double[2][2];

    @BeforeEach
    void setup() {
        result = new double[2][2];
    }

    @DisplayName("test serial multiply")
    @Test
    void testSerial() {
        SerialMultiplier.multiply(matrix1, matrix2, result);
        // Test Row 0
        // Test cell 0 0
        double cell00 = matrix1[0][0] * matrix2[0][0] + // 1*5 + 2*10 + 3*20
                    matrix1[0][1] * matrix2[1][0] +
                    matrix1[0][2] * matrix2[2][0];
        assertThat(result[0][0]).isEqualTo(cell00);

        // Test cell 0 1
        double cell01 = matrix1[0][0] * matrix2[0][1] + // 1*10 + 2*15 + 3*25
                matrix1[0][1] * matrix2[1][1] +
                matrix1[0][2] * matrix2[2][1];
        assertThat(result[0][1]).isEqualTo(cell01);

        // Test Row 1
        // Test cell 1 0
        double cell10 = matrix1[1][0] * matrix2[0][0] + // 4*5 + 5*10 + 6*20
                matrix1[1][1] * matrix2[1][0] +
                matrix1[1][2] * matrix2[2][0];
        assertThat(result[1][0]).isEqualTo(cell10);

        // Test cell 0 1
        double cell11 = matrix1[1][0] * matrix2[0][1] + // 4*10 + 5*15 + 6*25
                matrix1[1][1] * matrix2[1][1] +
                matrix1[1][2] * matrix2[2][1];
        assertThat(result[1][1]).isEqualTo(cell11);
    }
}
