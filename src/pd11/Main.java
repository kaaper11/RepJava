package pd11;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<Integer, User> registerUsers = new HashMap<>();

    static void checkEmail(String email) {
        for (User user : registerUsers.values()) {
            if (user.getEmail().equals(email)) {
                throw new DuplicateEmailException();
            }
        }
    }

    public static void main(String[] args) {
        int userID = 1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Wprowadz po kolei email, password oraz nazwe użytkownika:");
                String email = scanner.nextLine();
                System.out.println(email);
                String password = scanner.nextLine();
                String name = scanner.nextLine();
                UserRegistrationService.registerUser(name, email, password);
                checkEmail(email);

                registerUsers.put(1, User.of(name, email, "hashed_" + password));
                System.out.println("Dodano użytkownika!");

                System.out.println(registerUsers);
                userID++;
            } catch (WeakPasswordException e) {
                System.err.println(e.getMessage());
                break;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                System.out.println("Spróbuj jeszcze raz wprowadzić dane.");
                continue;
            } catch (DuplicateEmailException e) {
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
