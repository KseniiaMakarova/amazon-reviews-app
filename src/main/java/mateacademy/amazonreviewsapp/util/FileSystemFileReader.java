package mateacademy.amazonreviewsapp.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class FileSystemFileReader implements FileReader {
    @SneakyThrows
    @Override
    public List<String> readLines(Path path) {
        return Files.lines(path).skip(1).collect(Collectors.toList());
    }
}
