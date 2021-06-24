package concurrency.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to multiply matrices using the {@link RowMultiplierTask}
 */
public class ParallelRowMultiplierTask {

    /**
     * Multiplies two matrices matrix1 and matrix2 using the RowMultiplierTask runnable. The
     * results are stored in result.
     *
     * @param matrix1 matrix 1
     * @param matrix2 matrix 2
     * @param result stores result of multiply matrix1 with matrix2
     */
    public static void multiply(
            double[][] matrix1,
            double[][] matrix2,
            double[][] result
    ) {
        List<Thread> threads = new ArrayList<>();
        for(int matrix1RowIdx = 0; matrix1RowIdx < matrix1.length; matrix1RowIdx++) {
            RowMultiplierTask rowMultiplierTask = new RowMultiplierTask(result, matrix1, matrix2, matrix1RowIdx);
            Thread thread = new Thread(rowMultiplierTask);
            thread.start();
            threads.add(thread);
            waitIfNecessary(threads);
        }
    }

    /**
     * Wait for the current batch(list) of threads we started to all reach TERMINATED status before processing the next batch
     * @param threads list of threads
     */
    static void waitIfNecessary(List<Thread> threads) {
        if(threads.size() % 10 == 0){
            for(Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threads.clear();
        }
    }

}
