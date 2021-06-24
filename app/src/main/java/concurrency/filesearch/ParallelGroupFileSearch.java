package concurrency.filesearch;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupFileSearch {

    public static void searchFiles(File file,
                                   String fileName,
                                   Result result) {
        ConcurrentLinkedQueue<File> directories = new ConcurrentLinkedQueue<>();
        File[] contents = file.listFiles();
        assert contents != null;
        for (File content : contents)
            if (content.isDirectory())
                directories.add(content);

        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];
        GroupFileSearchTask[] tasks = new GroupFileSearchTask[numThreads];

        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new GroupFileSearchTask(directories, fileName, result);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (Thread thread : threads)
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
