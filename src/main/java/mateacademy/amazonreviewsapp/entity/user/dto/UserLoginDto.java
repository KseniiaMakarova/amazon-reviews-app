package mateacademy.amazonreviewsapp.entity.user.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotNull
    private String profileName;
    @NotNull
    private String password;
}
