package pd19.exception;

public class MemberHaveAlreadyAccountException extends RuntimeException {
    public MemberHaveAlreadyAccountException() {
        super("Użytkownik o takim emailu już istnieje.");
    }
}
