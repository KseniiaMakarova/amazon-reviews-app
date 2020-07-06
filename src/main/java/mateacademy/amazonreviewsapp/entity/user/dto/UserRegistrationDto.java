package mateacademy.amazonreviewsapp.entity.user.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import mateacademy.amazonreviewsapp.security.MatchingPasswordsConstraint;

@Data
@MatchingPasswordsConstraint
public class UserRegistrationDto {
    @NotNull
    private String profileName;
    private String password;
    private String repeatedPassword;
}
