package mateacademy.amazonreviewsapp.entity.user.service;

import java.util.List;
import java.util.Set;
import mateacademy.amazonreviewsapp.entity.user.User;
import mateacademy.amazonreviewsapp.entity.user.UserRepository;
import mateacademy.amazonreviewsapp.entity.user.dto.UserReviewsNumberDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveAll(Set<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public List<UserReviewsNumberDto> getMostActive(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.getMostActive(pageable).toList();
    }

    @Override
    public User get(String id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getByProfileName(String profileName) {
        return userRepository.getByProfileName(profileName);
    }
}
