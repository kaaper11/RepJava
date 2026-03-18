package pd03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String zad1(int liczba) {
        return liczba > 0 ? "positive" : "negative";
    }

    static String zad2(int liczba) {
        String wynikPodzilenosci;
        if (liczba % 2 == 0 && liczba % 3 == 0) {
            wynikPodzilenosci = "divisible by 2 and 3";
        } else if (liczba % 2 == 0) {
            wynikPodzilenosci = "divisible by 2";
        } else if (liczba % 3 == 0) {
            wynikPodzilenosci = "divisible by 3";
        } else {
            wynikPodzilenosci = "not divisible by 2 and 3";
        }
        return wynikPodzilenosci;
    }

    static String zad3(int liczba) {
        int abs = Math.abs(liczba);
        String rozmiarLiczby;

        if (abs > 1 && abs <= 10) {
            rozmiarLiczby = "small";
        } else if (abs > 11 && abs <= 100) {
            rozmiarLiczby = "medium";
        } else {
            rozmiarLiczby = "large";
        }
        return rozmiarLiczby;
    }

    static void zad4(int liczba) {
        System.out.printf("Number: %d, Type: %s, Divisibility: %s, Size: %s",
                liczba, zad1(liczba), zad2(liczba), zad3(liczba));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int liczba = sc.nextInt();
            if (liczba == 0) {
                throw new ArithmeticException();
            }
            zad4(liczba);
        } catch (ArithmeticException e) {
            System.out.println("0 nie używamy!");
        } catch (InputMismatchException e) {
            System.out.println("Wymagana liczba całkowita!");
        }

    }
}
