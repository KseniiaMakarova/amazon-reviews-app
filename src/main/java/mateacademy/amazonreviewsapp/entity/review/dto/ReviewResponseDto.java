package mateacademy.amazonreviewsapp.entity.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {
    private Long id;
    private String productId;
    private String userId;
    private int score;
    private String time;
    private String summary;
}
