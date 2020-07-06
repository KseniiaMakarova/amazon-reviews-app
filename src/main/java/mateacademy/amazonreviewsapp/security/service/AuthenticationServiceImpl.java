package mateacademy.amazonreviewsapp.security.service;

import java.util.Set;
import lombok.SneakyThrows;
import mateacademy.amazonreviewsapp.entity.role.Role;
import mateacademy.amazonreviewsapp.entity.role.service.RoleService;
import mateacademy.amazonreviewsapp.entity.user.User;
import mateacademy.amazonreviewsapp.entity.user.service.UserService;
import mateacademy.amazonreviewsapp.exception.InvalidCredentialsAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @Override
    public User login(String profileName, String password) {
        User user = userService.getByProfileName(profileName);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsAuthenticationException(
                    "The login or password is incorrect");
        }
        return user;
    }

    @SneakyThrows
    @Override
    public User register(String profileName, String password) {
        if (userService.getByProfileName(profileName) != null) {
            throw new InvalidCredentialsAuthenticationException(
                    "A user with this profile name already exists");
        }
        User user = new User();
        user.setProfileName(profileName);
        user.setPassword(passwordEncoder.encode(password));
        Role userRole = roleService.getByName("USER");
        user.setRoles(Set.of(userRole));
        return userService.save(user);
    }
}
