package pd19.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException() {
        super("Nie znaleziono wypożyczenia.");
    }
}
