package mateacademy.amazonreviewsapp.entity.review.service;

import java.time.LocalDateTime;
import java.util.List;
import mateacademy.amazonreviewsapp.entity.review.Review;
import mateacademy.amazonreviewsapp.entity.review.ReviewRepository;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewUpdateDto;
import mateacademy.amazonreviewsapp.entity.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> saveAll(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review get(Long id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public List<Review> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.findAll(pageable).toList();
    }

    @Override
    public List<Review> getAllByUser(int page, int size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.getAllByUser(pageable, user);
    }

    @Override
    public Review update(Review currentReview, ReviewUpdateDto reviewUpdateDto) {
        currentReview.setScore(reviewUpdateDto.getScore());
        currentReview.setTime(LocalDateTime.now());
        currentReview.setSummary(reviewUpdateDto.getSummary());
        currentReview.setText(reviewUpdateDto.getText());
        return reviewRepository.save(currentReview);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
