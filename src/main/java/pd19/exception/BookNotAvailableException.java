package pd19.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
        super("Książka nie jest obecnie dostępna do wypożyczenia.");
    }
}
