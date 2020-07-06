package mateacademy.amazonreviewsapp.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import mateacademy.amazonreviewsapp.security.BaseResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponse<List<String>> body = new BaseResponse<>();
        body.setTimestamp(LocalDateTime.now());
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.setValue(errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsAuthenticationException.class)
    public ResponseEntity<BaseResponse<String>> handleFailedAuthentication(Exception ex) {
        BaseResponse<String> body = new BaseResponse<>();
        body.setTimestamp(LocalDateTime.now());
        body.setValue(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleDeniedAccess(Exception ex) {
        BaseResponse<String> body = new BaseResponse<>();
        body.setTimestamp(LocalDateTime.now());
        body.setValue(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}
