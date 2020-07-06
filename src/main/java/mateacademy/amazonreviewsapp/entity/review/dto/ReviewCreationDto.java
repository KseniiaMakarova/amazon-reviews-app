package mateacademy.amazonreviewsapp.entity.review.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewCreationDto {
    @NotNull
    private String productId;
    @Min(1)
    @Max(5)
    private int score;
    @NotNull
    private String summary;
    private String text;
}
