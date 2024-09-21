package shad.wegri.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("토큰 검증에 실패하였습니다.");
    }
}
