package mateacademy.amazonreviewsapp.entity.review.service;

import java.util.List;
import mateacademy.amazonreviewsapp.entity.review.Review;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewUpdateDto;
import mateacademy.amazonreviewsapp.entity.user.User;

public interface ReviewService {
    Review save(Review review);

    List<Review> saveAll(List<Review> reviews);

    Review get(Long id);

    List<Review> getAll(int page, int size);

    List<Review> getAllByUser(int page, int size, User user);

    Review update(Review currentReview, ReviewUpdateDto reviewUpdateDto);

    void delete(Long id);
}
