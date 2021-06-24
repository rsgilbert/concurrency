package concurrency.filesearch;

import java.io.File;

/**
 * Searches for a file sequentially without taking advantage of multithreading
 */
public class SerialFileSearch {

    public static void searchFiles(File file,
                                   String fileName,
                                   Result result) {
        File[] contents;
        contents = file.listFiles();
        if(contents == null || contents.length == 0)
            return;

        for(File content: contents) {
            if(content.isDirectory()) {
                searchFiles(content, fileName, result);
            } else {
                // content is not a directory
                if(content.getName().equals(fileName)) {
                    result.setFound(true);
                    result.setPath(content.getPath());
                }
            }
            if(result.isFound())
                return;
        }
    }
}
