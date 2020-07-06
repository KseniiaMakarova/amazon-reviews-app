package mateacademy.amazonreviewsapp.entity.word;

import java.util.List;
import java.util.stream.Collectors;
import mateacademy.amazonreviewsapp.entity.word.dto.WordMapper;
import mateacademy.amazonreviewsapp.entity.word.dto.WordUseCountDto;
import mateacademy.amazonreviewsapp.entity.word.service.WordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordController {
    private final WordService wordService;
    private final WordMapper wordMapper;

    public WordController(WordService wordService, WordMapper wordMapper) {
        this.wordService = wordService;
        this.wordMapper = wordMapper;
    }

    @GetMapping("/most-used")
    public List<WordUseCountDto> getMostUsedWords(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1000") int size) {
        return wordService.getMostUsed(page, size)
                .stream()
                .map(wordMapper::getWordResponseDto)
                .collect(Collectors.toList());
    }
}
