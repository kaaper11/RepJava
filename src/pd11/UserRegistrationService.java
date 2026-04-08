package pd11;

public class UserRegistrationService {

    public static void registerUser(String name, String email, String password) throws RegistrationException {
        validateEmail(email);
        validatePassword(password);
        validateName(name);
    }

    private static void validatePassword(String password) {
        System.out.println(password);
        if (password.length() < 8 || password == null) {
            throw new WeakPasswordException("długość.", "Minimum 8 znaków");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new WeakPasswordException("litery.", "Co najmniej 1 wielka litera");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new WeakPasswordException("cyfry.", "Co najmniej 1 cyfra.");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new WeakPasswordException("znaki specjalne", "Co najmniej 1 znak specjalny");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !email.matches("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{1,}")) {
            throw new ValidationException("email");
        }
    }

    private static void validateName(String name) {
        if (name == null || !name.matches("[a-zA-Z\\s\\-]+") || name.length() < 2 || name.length() > 100) {
            throw new ValidationException("name");
        }
    }
}
