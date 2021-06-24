package concurrency.filesearch;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Runnable class for searching a file within a group of files (or directories).
 */
public class GroupFileSearchTask implements Runnable {

    private boolean found;
    private final String fileName;
    private final Result result;
    private final ConcurrentLinkedQueue<File> directories;

    /**
     * Construct for search task
     */
    public GroupFileSearchTask(
            ConcurrentLinkedQueue<File> directories,
            String fileName,
            Result result
    ) {
        this.fileName = fileName;
        this.result = result;
        this.directories = directories;
    }

    /**
     * Method for performing the file search
     */
    @Override
    public void run() {
        while (directories.size() > 0) {
            File file = directories.poll();
            // If file is null, then all directories to search in have been assigned to a thread.
            // In this case we are done
            if (file == null)
                return;

            processDirectory(file, fileName, result);
            if (result.isFound()) {
                return;
            }
        }
    }

    /**
     * Private utility method for performing a search within a directory
     */
    private void processDirectory(File file, String fileName, Result result) {
        File[] contents = file.listFiles();
        if(contents == null)
            return;
        for (File content : contents) {
            if (result.isFound())
                return;
            if (content.isDirectory()) {
                processDirectory(content, fileName, result);
            } else
                processFile(content, fileName, result);
        }
    }

    /**
     * Utility method for processing a single file
     */
    private void processFile(File file, String fileName, Result result) {
        if (file.getName().equals(fileName)) {
            result.setFound(true);
            result.setPath(file.getPath());
        }
    }
}
