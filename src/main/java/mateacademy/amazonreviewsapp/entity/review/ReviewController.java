package mateacademy.amazonreviewsapp.entity.review;

import java.nio.file.AccessDeniedException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.SneakyThrows;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewCreationDto;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewMapper;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewResponseDto;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewUpdateDto;
import mateacademy.amazonreviewsapp.entity.review.service.ReviewService;
import mateacademy.amazonreviewsapp.entity.user.User;
import mateacademy.amazonreviewsapp.entity.user.service.UserService;
import mateacademy.amazonreviewsapp.util.DataInjector;
import mateacademy.amazonreviewsapp.util.FileReader;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final FileReader fileReader;
    private final ReviewMapper reviewMapper;
    private final UserService userService;
    private final ReviewService reviewService;
    private final DataInjector dataInjector;

    public ReviewController(FileReader fileReader, ReviewMapper reviewMapper,
                            UserService userService, ReviewService reviewService,
                            DataInjector dataInjector) {
        this.fileReader = fileReader;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
        this.reviewService = reviewService;
        this.dataInjector = dataInjector;
    }

    @GetMapping("/")
    public List<ReviewResponseDto> getAllReviews(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1000") int size) {
        return reviewService.getAll(page, size)
                .stream()
                .map(reviewMapper::getReviewResponseDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @GetMapping("/by-user")
    public List<ReviewResponseDto> getAllReviews(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1000") int size,
            @RequestParam(required = false) String id,
            Authentication authentication) {
        User targetUser = id == null
                ? userService.getByProfileName(authentication.getName())
                : userService.get(id);
        return reviewService.getAllByUser(page, size, targetUser)
                .stream()
                .map(reviewMapper::getReviewResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public ReviewResponseDto createReview(@RequestBody @Valid ReviewCreationDto reviewCreationDto,
                                          Authentication authentication) {
        User user = userService.getByProfileName(authentication.getName());
        Review review = reviewMapper.getReview(reviewCreationDto, user);
        return reviewMapper.getReviewResponseDto(reviewService.save(review));
    }

    @SneakyThrows
    @PutMapping("/")
    public ReviewResponseDto editReview(
            @RequestBody @Valid ReviewUpdateDto reviewUpdateDto,
            Authentication authentication) {
        User currentUser = userService.getByProfileName(authentication.getName());
        Review currentReview = reviewService.get(reviewUpdateDto.getId());
        if (!currentUser.equals(currentReview.getUser())) {
            throw new AccessDeniedException(
                    "It seems you are trying to edit a review of a different user");
        }
        Review updatedReview = reviewService.update(currentReview, reviewUpdateDto);
        return reviewMapper.getReviewResponseDto(updatedReview);
    }

    @DeleteMapping("/")
    public String deleteReview(@RequestParam Long id) {
        reviewService.delete(id);
        return "The review with id " + id + " was deleted";
    }

    @PostMapping("/load")
    public String loadData(
            @RequestParam(required = false,
                    defaultValue = "src/main/resources/static/Reviews.csv")
                    String path) {
        List<String> lines = fileReader.readLines(Paths.get(path));
        dataInjector.injectDataFromFile(lines);
        return "The data was successfully downloaded to DB";
    }
}
