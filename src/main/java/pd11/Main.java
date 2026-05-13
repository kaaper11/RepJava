package pd11;

import java.util.Scanner;

import static pd11.UserRegistrationService.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Wprowadz po kolei email, password oraz nazwe użytkownika:");
                String email = scanner.nextLine();
                System.out.println(email);
                String password = scanner.nextLine();
                String name = scanner.nextLine();
                registerUser(name, email, password);
                checkEmail(email);

                addUser(User.of(name, email, "hashed_" + password));
                System.out.println("Dodano użytkownika!");

                System.out.println(registerUsers);
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
