package mateacademy.amazonreviewsapp.entity.word.service;

import java.util.List;
import mateacademy.amazonreviewsapp.entity.word.Word;
import mateacademy.amazonreviewsapp.entity.word.WordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> saveAll(List<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public List<Word> getMostUsed(int page, int size) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by("useCount")
                        .descending()
                        .and(Sort.by("value")));
        return wordRepository.findAll(pageable).toList();
    }

}
