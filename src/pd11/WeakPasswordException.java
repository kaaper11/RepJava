package pd11;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String score, String details) {
        super("Hasło za słabe: typ " + score + details);
    }
}
