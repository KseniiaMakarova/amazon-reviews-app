package mateacademy.amazonreviewsapp.security.service;

import mateacademy.amazonreviewsapp.entity.user.User;

public interface AuthenticationService {
    User login(String email, String password);

    User register(String email, String password);
}
