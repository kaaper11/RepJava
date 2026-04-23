package pd16;

public class RentException extends RuntimeException {
    public RentException(Status status) {
        super("Gra jest: " + status + ", nie możesz wykonać czynnośći!");
    }
}
