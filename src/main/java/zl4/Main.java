package zl4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Password correctPassword = new Password("haslo123");
        int licznik = 0;

        System.out.println("Podaj hasło");

        while (licznik < 3) {
            String userPassword = sc.nextLine();
            if (userPassword.length() < 3) {
                System.out.println("Password too short");
                continue;
            }
            if (correctPassword.isCorrectPassword(userPassword)) {
                System.out.println("Login successful");
                break;
            }
            System.out.println("Wrong password");
            licznik++;

        }
        if (licznik == 3) {
            System.out.println("Account locked");
        }
    }
}
