package mateacademy.amazonreviewsapp.security;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mateacademy.amazonreviewsapp.entity.user.dto.UserRegistrationDto;

public class MatchingPasswordsValidator
        implements ConstraintValidator<MatchingPasswordsConstraint, UserRegistrationDto> {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 30;

    @Override
    public boolean isValid(
            UserRegistrationDto userRegistrationDto, ConstraintValidatorContext context) {
        String passwordValue = userRegistrationDto.getPassword();
        String repeatedPasswordValue = userRegistrationDto.getRepeatedPassword();
        return passwordValue != null && passwordValue.equals(repeatedPasswordValue)
                && passwordValue.length() >= MIN_LENGTH && passwordValue.length() <= MAX_LENGTH;
    }
}
