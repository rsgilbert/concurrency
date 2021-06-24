package concurrency.filesearch;

import concurrency.TimingExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(TimingExtension.class)
class SerialFileSearchTest {

    @BeforeEach
    void setUp() {
    }

    @Test // Appr 1280 ms 1.8 s
    void searchFiles() {
        String startPath = "/home/ssenyonjo/Projects";
        File f = new File(startPath);
        Result result = new Result();
        SerialFileSearch.searchFiles(f, "ParallelGroupFileSearch.java", result);
        System.out.println(result.getPath());
    }
}