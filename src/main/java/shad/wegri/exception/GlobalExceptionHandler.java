package shad.wegri.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchMemberException.class, AlreadyExistMemberException.class, NoSuchProductException.class})
    public ProblemDetail handleInvalidTokenException(RuntimeException runtimeException) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(runtimeException.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ProblemDetail handleInvalidTokenException(InvalidTokenException invalidTokenException) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setDetail(invalidTokenException.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(InvalidKakaoTokenException.class)
    public ProblemDetail handleInvalidKakaoTokenException(InvalidKakaoTokenException invalidKakaoTokenException) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(invalidKakaoTokenException.getStatusCode());
        problemDetail.setDetail(invalidKakaoTokenException.getReason());
        return problemDetail;
    }
}
