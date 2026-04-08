package pd11;

public class ValidationException extends RegistrationException {
    public ValidationException(String field) {
        super("Błąd walidacji pola: " + field);
    }
}
