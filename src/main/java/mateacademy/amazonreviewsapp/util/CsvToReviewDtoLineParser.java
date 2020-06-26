package mateacademy.amazonreviewsapp.util;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import mateacademy.amazonreviewsapp.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class CsvToReviewDtoLineParser implements CsvLineParser<ReviewDto> {
    @Override
    public ReviewDto parseLine(String line) {
        CsvParser csvParser = new CsvParser(new CsvParserSettings());
        String[] data = csvParser.parseLine(line);
        return getReviewDto(data);
    }

    public ReviewDto getReviewDto(String[] data) {
        return ReviewDto.builder()
                .id(Long.valueOf(data[0]))
                .productId(data[1])
                .userId(data[2])
                .profileName(data[3])
                .helpfulnessNumerator(Integer.parseInt(data[4]))
                .helpfulnessDenominator(Integer.parseInt(data[5]))
                .score(Integer.parseInt(data[6]))
                .time(Long.parseLong(data[7]))
                .summary(data[8])
                .text(data[9])
                .build();
    }
}
