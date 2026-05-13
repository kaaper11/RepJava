package pd16.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String object) {
        super("Nie znaleziono '" + object + "' w bazie danych!");
    }
}
