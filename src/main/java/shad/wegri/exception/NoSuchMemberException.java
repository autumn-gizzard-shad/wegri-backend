package shad.wegri.exception;

public class NoSuchMemberException extends RuntimeException {

    public NoSuchMemberException() {
        super("가입되지 않은 회원입니다.");
    }
}
