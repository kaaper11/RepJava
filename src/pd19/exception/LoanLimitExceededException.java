package pd19.exception;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException() {
        super("Maksymalnie 3 wypożyczenia.");
    }
}
