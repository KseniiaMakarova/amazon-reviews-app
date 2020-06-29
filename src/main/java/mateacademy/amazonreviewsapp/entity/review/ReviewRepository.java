package mateacademy.amazonreviewsapp.entity.review;

import java.util.List;
import mateacademy.amazonreviewsapp.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getAllByUser(Pageable pageable, User user);
}
