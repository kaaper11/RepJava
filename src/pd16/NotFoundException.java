package pd16;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String object) {
        super("Nie znaleziono '" + object + "' w bazie danych!");
    }
}
