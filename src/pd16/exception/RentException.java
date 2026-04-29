package pd16.exception;

import pd16.entity.Status;

public class RentException extends RuntimeException {
    public RentException(Status status) {
        super("Gra jest: " + status + ", nie możesz wykonać czynnośći!");
    }
}
