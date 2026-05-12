package pd19.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Nie zanelziono podanej książki");
    }
}
