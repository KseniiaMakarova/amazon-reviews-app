package mateacademy.amazonreviewsapp.security;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javax.validation.Valid;
import mateacademy.amazonreviewsapp.entity.user.User;
import mateacademy.amazonreviewsapp.entity.user.dto.UserLoginDto;
import mateacademy.amazonreviewsapp.entity.user.dto.UserMapper;
import mateacademy.amazonreviewsapp.entity.user.dto.UserRegistrationDto;
import mateacademy.amazonreviewsapp.entity.user.dto.UserResponseDto;
import mateacademy.amazonreviewsapp.security.jwt.JwtTokenProvider;
import mateacademy.amazonreviewsapp.security.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        User user = authenticationService.register(
                userRegistrationDto.getProfileName(), userRegistrationDto.getPassword());
        return userMapper.getUserResponseDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(
            @RequestBody @Valid UserLoginDto userLoginDto) {
        User user = authenticationService.login(
                userLoginDto.getProfileName(), userLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(user.getProfileName(),
                user.getRoles().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toList()));
        BaseResponse<String> body = new BaseResponse<>();
        body.setTimestamp(LocalDateTime.now());
        body.setValue(token);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
