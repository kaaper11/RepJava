package pd11;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException() {
        super("Email już istnieje");
    }
}
