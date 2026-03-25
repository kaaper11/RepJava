package pd06;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MathLibrary {
    /**
     * Metoda liczy silnie rekurencyjnie liczby podanej przez użytkownika jako parametr.
     *
     * @param n liczba, której silnie chcemy obliczać
     * @return wynik oblczenia silni
     */
    static long recFactorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * recFactorial(n - 1);
        }
    }

    /**
     * Metoda liczy silnie iteracyjnie liczby podanej przez użytkownika jako parametr.
     *
     * @param n liczba, której silnie chcemy obliczać
     * @return wynik oblczenia silni
     */
    static long secFactorial(int n) {
        int factorialResult = 1;
        for (int i = 1; i <= n; i++) {
            factorialResult *= i;
        }
        return factorialResult;
    }

    /**
     * Metoda sprawdza czy liczba podana jako argument jest liczbą pierwszą.
     *
     * @param n liczba, którą sparwdzamy czy jest pierwsza
     * @return wynik sprawdzenia czy liczba jest pierwsza
     */
    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metoda sprawdza, które liczby z konkretnego zakresu są pierwsze.
     *
     * @param limit liczba, które określa limit przedziału
     * @return tablica przechowująca wyniki [0,1], czy liczba jest pierwsza
     */
    static int[] sieveOfEratosthenes(int limit) {
        int[] prime = new int[limit];

        for (int i = 2; i < limit; i++) {
            prime[i] = 1;
        }

        for (int i = 2; i < limit; i++) {
            if (prime[i] == 1) {
                for (int j = i * i; j < limit; j += i) {
                    prime[j] = 0;
                }
            }
        }
        return prime;
    }

    /**
     * Metoda znajduje największy wspólny dzielnik dwóch liczb.
     *
     * @param a pierwsza liczba całkowita
     * @param b druga liczba całkowita
     * @return największy wspólny dzielnik liczb a i b
     * throws ArithmeticException w sytuacji, kiedy b = 0
     */
    static int gcd(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Dzielenie przez zero");
        }
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
     * Metoda komunukuje się z użytkownikiem i wczytuje odpowiedni metody oraz argumenty tych metod.
     */
    static void methodMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj liczbe która odpowiada konkretnej metodzie.");
        System.out.println("""
                1 - Silnia rekurencyjnie
                2 - Silnia iteracyjnie
                3 - Sprawdzenie czy liczba jest pierwsza
                4 - sito Eratostenesa
                5 - algorytm Euklidesa
                """);
        switch (sc.nextInt()) {
            case 1 -> {
                System.out.println("Podaj liczbe której silnie chcesz obliczyć");
                System.out.println("Wynik silni: " + recFactorial(sc.nextInt()));
            }
            case 2 -> {
                System.out.println("Podaj liczbe której silnie chcesz obliczyć");
                System.out.println("Wynik silni: " + secFactorial(sc.nextInt()));
            }
            case 3 -> {
                System.out.println("Podaj liczbe którą chcesz sprawdzić czy jest pierwsza");
                System.out.println("Wynik czy liczba jest pierwsza: " + isPrime(sc.nextInt()));
            }
            case 4 -> {
                System.out.println("Podaj liczbe do jakiej metoda ma sparwdzić liczby pierwsze");
                int[] sieveResult = sieveOfEratosthenes(sc.nextInt());
                for (int i = 0; i < sieveResult.length; i++) {
                    if (sieveResult[i] == 1) {
                        System.out.print(i + " ");
                    }
                }
            }
            case 5 -> {
                System.out.println("Podaj dwie liczby których największy wspólny dzielnik chcesz znaleźć");
                System.out.println("Najwikszy wspolny dzielnik twoich liczb: " + gcd(sc.nextInt(), sc.nextInt()));
            }
        }
    }
}
