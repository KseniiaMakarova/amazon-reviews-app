package mateacademy.amazonreviewsapp.entity.word.dto;

import java.util.Map;
import mateacademy.amazonreviewsapp.entity.word.Word;
import org.springframework.stereotype.Component;

@Component
public class WordMapper {
    public WordUseCountDto getWordResponseDto(Word word) {
        WordUseCountDto wordUseCountDto = new WordUseCountDto();
        wordUseCountDto.setValue(word.getValue());
        wordUseCountDto.setUseCount(word.getUseCount());
        return wordUseCountDto;
    }

    public Word getWord(Map.Entry<String, Integer> entry) {
        Word word = new Word();
        word.setValue(entry.getKey());
        word.setUseCount(entry.getValue());
        return word;
    }
}
