package mateacademy.amazonreviewsapp.entity.word.service;

import java.util.List;
import mateacademy.amazonreviewsapp.entity.word.Word;

public interface WordService {
    List<Word> saveAll(List<Word> words);

    List<Word> getMostUsed(int page, int size);
}
