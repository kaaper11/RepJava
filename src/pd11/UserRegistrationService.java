package pd11;

import java.util.HashMap;


public class UserRegistrationService {
    static HashMap<Integer, User> registerUsers = new HashMap<>();
    private static int userID = 1;

    public static void addUser(User user) {
        registerUsers.put(userID++, user);
    }

    public static void registerUser(String name, String email, String password) throws RegistrationException {
        validateEmail(email);
        validatePassword(password);
        validateName(name);
        System.out.println(passwordStrength(password));
    }

    static void checkEmail(String email) {
        for (User user : registerUsers.values()) {
            if (user.getEmail().equals(email)) {
                throw new DuplicateEmailException();
            }
        }
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

    private static PasswordStrength passwordStrength(String password) {
        if ((password.length() >=8 && password.length() < 10) ||  password.matches("^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=[^0-9]*\\d[^0-9]*$)(?=[^A-Za-z0-9]*[^A-Za-z0-9][^A-Za-z0-9]*$)[^\\n]*$")){
            return PasswordStrength.WEAK;
        } else if ((password.length() >= 10 && password.length() < 16) || password.matches("^(?=(?:[^A-Z]*[A-Z]){2,5}[^A-Z]*$)" +
                "(?=(?:[^0-9]*\\d){2,5}[^0-9]*$)" +
                "(?=(?:[A-Za-z0-9]*[^A-Za-z0-9]){2,5}[A-Za-z0-9]*$).+$")) {
            return PasswordStrength.OK;
        }
        return PasswordStrength.STRONG;

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
