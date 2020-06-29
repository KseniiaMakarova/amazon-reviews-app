package mateacademy.amazonreviewsapp.entity.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewSourceDto {
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private long time;
    private String summary;
    private String text;
}
