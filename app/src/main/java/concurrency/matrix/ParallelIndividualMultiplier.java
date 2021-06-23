package concurrency.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates all the execution threads necessary to calculate the result matrix.
 */
public class ParallelIndividualMultiplier {
    public static void multiply(
            double[][] matrix1,
            double[][] matrix2,
            double[][] result) {
        List<Thread> threads = new ArrayList<>();
        int rows1 = matrix1.length;
        int columns2 = matrix2[0].length;
        Thread.State status;


        for(int i = 0; i < rows1; i++){
            for(int j = 0; j < columns2; j++) {
                var task = new IndividualMultiplierTask(result, matrix1, matrix2, i, j);
                Thread thread = new Thread(task);
                status = thread.getState();
                System.out.println("thread status is " + thread.getState());
                thread.start();
                System.out.println("thread status is " + thread.getState());
                status = thread.getState();
                System.out.println(status); // termin // run
                  System.out.printf("Thread at %d %d is %s\n", i, j, thread.getName());
                threads.add(thread);

                System.out.println("thread status is " + thread.getState());
                status = thread.getState();

                if(threads.size() % 10 == 0) {
                    waitForThreads(threads);
                }
            }
        }
    }

    /**
     * Wait for the given list of threads to complete execution and terminate.
     * @param threads list of threads to wait for
     */
    private static void waitForThreads(List<Thread> threads) {
        // System.out.printf("We have %d threads\n", threads.size());
        for(var thread: threads) {
            try {

                System.out.println(thread.getState());
                thread.join();
                System.out.println(thread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
