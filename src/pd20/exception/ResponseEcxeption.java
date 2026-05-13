package pd20.exception;

public class ResponseEcxeption extends RuntimeException {
    public ResponseEcxeption(String api) {
        super("Błąd pobierania API: " + api);
    }
}
