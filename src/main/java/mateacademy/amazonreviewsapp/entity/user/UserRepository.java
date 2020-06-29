package mateacademy.amazonreviewsapp.entity.user;

import mateacademy.amazonreviewsapp.entity.user.dto.UserReviewsNumberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT u.id AS id, u.profileName AS profileName, COUNT(r) AS reviewsNumber "
            + "FROM Review r JOIN r.user u "
            + "GROUP BY u ORDER BY reviewsNumber DESC, profileName ASC")
    Page<UserReviewsNumberDto> getMostActive(Pageable pageable);

    @Query(value = "SELECT u FROM User u JOIN FETCH u.roles r "
            + "WHERE u.profileName = :profileName")
    User getByProfileName(String profileName);
}
