package concurrency.filesearch;

import concurrency.TimingExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests parallel group file search
 */
@ExtendWith(TimingExtension.class)
class ParallelGroupFileSearchTest {

    @BeforeEach
    void setUp() {
    }

    @Test // Runs appr 9 s
    void parallelSearchFiles() {
        // Test parallel file search
        String startPath = "/";
        String searchWord = "feedback.zip";
        File file = new File(startPath);
        Result result = new Result();

        ParallelGroupFileSearch.searchFiles(file, searchWord, result);
        System.out.println(result);

    }

    @Test // Takes a very long time
    void serialSearchFiles() {
        // Test parallel file search
        String startPath = "/";
        String searchWord = "feedback.zip";
        File file = new File(startPath);
        Result result = new Result();

        SerialFileSearch.searchFiles(file, searchWord, result);
        System.out.println(result);
    }
}