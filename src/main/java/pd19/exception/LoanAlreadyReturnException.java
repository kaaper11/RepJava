package pd19.exception;

public class LoanAlreadyReturnException extends RuntimeException {
    public LoanAlreadyReturnException() {
        super("Wypożyczenie już się zakończyło, nie możesz ponowanie go zakończyć.");
    }
}
