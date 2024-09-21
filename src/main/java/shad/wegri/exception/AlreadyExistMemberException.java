package shad.wegri.exception;

public class AlreadyExistMemberException extends RuntimeException {

    public AlreadyExistMemberException() {
        super("이미 가입된 회원입니다.");
    }
}
