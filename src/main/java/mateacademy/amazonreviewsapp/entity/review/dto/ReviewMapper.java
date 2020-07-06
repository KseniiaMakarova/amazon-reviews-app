package mateacademy.amazonreviewsapp.entity.review.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import mateacademy.amazonreviewsapp.entity.product.Product;
import mateacademy.amazonreviewsapp.entity.product.service.ProductService;
import mateacademy.amazonreviewsapp.entity.review.Review;
import mateacademy.amazonreviewsapp.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private final ProductService productService;

    public ReviewMapper(ProductService productService) {
        this.productService = productService;
    }

    public ReviewResponseDto getReviewResponseDto(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .userId(review.getUser().getId())
                .score(review.getScore())
                .time(review.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .summary(review.getSummary())
                .build();
    }

    public Product getProduct(ReviewSourceDto reviewSourceDto) {
        Product product = new Product();
        product.setId(reviewSourceDto.getProductId());
        return product;
    }

    public User getUser(ReviewSourceDto reviewSourceDto) {
        User user = new User();
        user.setId(reviewSourceDto.getUserId());
        user.setProfileName(reviewSourceDto.getProfileName());
        return user;
    }

    public Review getReview(ReviewCreationDto reviewCreationDto, User user) {
        return Review.builder()
                .product(productService.get(reviewCreationDto.getProductId()))
                .user(user)
                .score(reviewCreationDto.getScore())
                .time(LocalDateTime.now())
                .summary(reviewCreationDto.getSummary())
                .text(reviewCreationDto.getText())
                .build();
    }

    public Review getReview(ReviewSourceDto reviewSourceDto, Product product, User user) {
        return Review.builder()
                .id(reviewSourceDto.getId())
                .product(product)
                .user(user)
                .helpfulnessNumerator(reviewSourceDto.getHelpfulnessNumerator())
                .helpfulnessDenominator(reviewSourceDto.getHelpfulnessDenominator())
                .score(reviewSourceDto.getScore())
                .time(parseTime(reviewSourceDto.getTime()))
                .summary(reviewSourceDto.getSummary())
                .text(reviewSourceDto.getText())
                .build();
    }

    private LocalDateTime parseTime(long timeInMillis) {
        Instant instant = Instant.ofEpochSecond(timeInMillis);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
