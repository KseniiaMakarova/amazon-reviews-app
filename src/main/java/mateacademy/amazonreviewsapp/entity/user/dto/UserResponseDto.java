package mateacademy.amazonreviewsapp.entity.user.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String profileName;
    private Set<String> roles;
}
