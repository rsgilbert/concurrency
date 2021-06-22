package concurrency.matrix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class MatrixMultiplicationTest {

    @DisplayName("log generate()")
    @Test
    void logGenerate() {
        var matrix = MatrixGenerator.generate(5, 3);
        for(var col: matrix) {
            for(var cell: col) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}
