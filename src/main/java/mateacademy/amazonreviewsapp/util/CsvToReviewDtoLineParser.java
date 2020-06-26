package mateacademy.amazonreviewsapp.util;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import mateacademy.amazonreviewsapp.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class CsvToReviewDtoLineParser implements CsvLineParser<ReviewDto> {
    private static final int REVIEW_ID_INDEX = 0;
    private static final int PRODUCT_ID_INDEX = 1;
    private static final int USER_ID_INDEX = 2;
    private static final int PROFILE_NAME_INDEX = 3;
    private static final int HELP_NUMERATOR_INDEX = 4;
    private static final int HELP_DENOMINATOR_INDEX = 5;
    private static final int SCORE_INDEX = 6;
    private static final int TIME_INDEX = 7;
    private static final int SUMMARY_INDEX = 8;
    private static final int TEXT_INDEX = 9;

    @Override
    public ReviewDto parseLine(String line) {
        CsvParser csvParser = new CsvParser(new CsvParserSettings());
        String[] data = csvParser.parseLine(line);
        return getReviewDto(data);
    }

    private ReviewDto getReviewDto(String[] data) {
        return ReviewDto.builder()
                .id(Long.valueOf(data[REVIEW_ID_INDEX]))
                .productId(data[PRODUCT_ID_INDEX])
                .userId(data[USER_ID_INDEX])
                .profileName(data[PROFILE_NAME_INDEX])
                .helpfulnessNumerator(Integer.parseInt(data[HELP_NUMERATOR_INDEX]))
                .helpfulnessDenominator(Integer.parseInt(data[HELP_DENOMINATOR_INDEX]))
                .score(Integer.parseInt(data[SCORE_INDEX]))
                .time(Long.parseLong(data[TIME_INDEX]))
                .summary(data[SUMMARY_INDEX])
                .text(data[TEXT_INDEX])
                .build();
    }
}
