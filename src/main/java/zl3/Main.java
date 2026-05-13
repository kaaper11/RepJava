package zl3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj liczbe dnia tygodnia od 1-7");
        int dzienTygodnia = sc.nextInt();

        switch (dzienTygodnia) {
            case 1 -> System.out.println("Poniedziałek");
            case 2 -> System.out.println("Wtorek");
            case 3 -> System.out.println("Środa");
            case 4 -> System.out.println("Czwartek");
            case 5 -> System.out.println("Piątek");
            case 6 -> System.out.println("Sobota");
            case 7 -> System.out.println("Niedziela");
            default -> throw new RuntimeException("Nieprawidłowa wartość");
        }

        String okresTygodnia = dzienTygodnia < 6 ? "dzien roboczy" : "weekend";
        System.out.println(okresTygodnia);

        switch (dzienTygodnia) {
            case 1, 2, 3, 4, 5 -> System.out.println("autobus wyjeżdża o 5:30");
            case 6 -> System.out.println("autobus wyjeżdża o 7:00");
            case 7 -> System.out.println("autobus wyjeżdża o 9:00");
        }
    }
}
