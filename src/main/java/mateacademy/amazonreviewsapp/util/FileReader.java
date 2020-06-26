package mateacademy.amazonreviewsapp.util;

import java.nio.file.Path;
import java.util.List;

public interface FileReader {
    List<String> readLines(Path path);
}
