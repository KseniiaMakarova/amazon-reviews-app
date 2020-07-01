package mateacademy.amazonreviewsapp.util;

public interface LineParser<T> {
    T parseLine(String line);

    int getOffset();
}
