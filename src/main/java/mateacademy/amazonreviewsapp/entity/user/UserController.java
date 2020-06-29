package mateacademy.amazonreviewsapp.entity.user;

import java.util.List;
import mateacademy.amazonreviewsapp.entity.user.dto.UserReviewsNumberDto;
import mateacademy.amazonreviewsapp.entity.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/most-active")
    public List<UserReviewsNumberDto> getMostActiveUsers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1000") int size) {
        return userService.getMostActive(page, size);
    }
}
