package pd19.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("Nie znaleziono użytkownika.");
    }
}
