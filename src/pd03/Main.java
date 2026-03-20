package pd03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String typeOfNumber(int liczba) {
        return liczba > 0 ? "positive" : "negative";
    }

    static String divisibilityOfNumber(int liczba) {
        if (liczba % 6 == 0) {
            return "divisible by 2 and 3";
        } else if (liczba % 2 == 0) {
            return "divisible by 2";
        } else if (liczba % 3 == 0) {
            return "divisible by 3";
        } else {
            return "not divisible by 2 and 3";
        }
    }

    static String sizeOfNumber(int liczba) {
        int abs = Math.abs(liczba);

        if (abs > 1 && abs <= 10) {
            return "small";
        } else if (abs > 11 && abs <= 100) {
            return "medium";
        } else {
            return "large";
        }
    }

    static void printNumber(int liczba) {
        System.out.printf("Number: %d, Type: %s, Divisibility: %s, Size: %s",
                liczba, typeOfNumber(liczba), divisibilityOfNumber(liczba), sizeOfNumber(liczba));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int liczba = sc.nextInt();
            if (liczba == 0) {
                throw new ArithmeticException();
            }
            printNumber(liczba);
        } catch (ArithmeticException e) {
            System.out.println("0 nie używamy!");
        } catch (InputMismatchException e) {
            System.out.println("Wymagana liczba całkowita!");
        }

    }
}
