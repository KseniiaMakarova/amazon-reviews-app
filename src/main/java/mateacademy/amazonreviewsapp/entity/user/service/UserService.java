package mateacademy.amazonreviewsapp.entity.user.service;

import java.util.List;
import java.util.Set;
import mateacademy.amazonreviewsapp.entity.user.User;
import mateacademy.amazonreviewsapp.entity.user.dto.UserReviewsNumberDto;

public interface UserService {
    User save(User user);

    List<User> saveAll(Set<User> users);

    List<UserReviewsNumberDto> getMostActive(int page, int size);

    User get(String id);

    User getByProfileName(String profileName);
}
