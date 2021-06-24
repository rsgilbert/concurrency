package concurrency.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs matrix multiplications in parallel using {@link GroupMultiplierTask}
 */
public class ParallelGroupMultiplier {

    public static void multiply(
            double[][] matrix1,
            double[][] matrix2,
            double[][] result
    ) {
        int matrix1Rows = matrix1.length;
        int cpuCount = Runtime.getRuntime().availableProcessors();
        int rowsPerThread = (int) Math.ceil((double) matrix1Rows / cpuCount);

        List<Thread> threadList = new ArrayList<>();
        for(int idx = 0; idx < matrix1Rows; idx += rowsPerThread) {
            int endIdx = Math.min(matrix1Rows, idx + rowsPerThread);
            var task = new GroupMultiplierTask(result, matrix1, matrix2, idx, endIdx);
            var thread = new Thread(task);
            thread.start();
            threadList.add(thread);
        }

        for(var thread: threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
