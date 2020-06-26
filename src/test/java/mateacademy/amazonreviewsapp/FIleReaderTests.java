package mateacademy.amazonreviewsapp;

import java.nio.file.Paths;
import java.util.List;
import mateacademy.amazonreviewsapp.util.FileReader;
import mateacademy.amazonreviewsapp.util.FileSystemFileReader;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FIleReaderTests {
    private final FileReader fileReader = new FileSystemFileReader();

    @Test
    void readLines_Ok() {
        List<String> actual = fileReader.readLines(Paths.get("src/test/resources/test.txt"));
        Assert.assertEquals(actual, List.of("1,2,3", "4,5,6"));
    }
}
