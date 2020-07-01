package mateacademy.amazonreviewsapp.security;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private T value;
    private LocalDateTime timestamp;
}
