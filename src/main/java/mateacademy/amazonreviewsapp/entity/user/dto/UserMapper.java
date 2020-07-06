package mateacademy.amazonreviewsapp.entity.user.dto;

import java.util.stream.Collectors;
import mateacademy.amazonreviewsapp.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setProfileName(user.getProfileName());
        userResponseDto.setRoles(user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet()));
        return userResponseDto;
    }
}
