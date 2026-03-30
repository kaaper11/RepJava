package pd06;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MathLibrary {
    /**
     * Method recursively calculates the factorial of the number given as a parameter.
     *
     * @param n the number for which we want to calculate the factorial
     * @return the result of the factorial calculation
     */
    static long recFactorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * recFactorial(n - 1);
        }
    }

    /**
     * Method iteratively calculates the factorial of the number given as a parameter.
     *
     * @param n the number for which we want to calculate the factorial
     * @return the result of the factorial calculation
     */
    static long secFactorial(int n) {
        int factorialResult = 1;
        for (int i = 1; i <= n; i++) {
            factorialResult *= i;
        }
        return factorialResult;
    }

    /**
     * Method checks whether the number provided as an argument is prime.
     *
     * @param n the number to check for primality
     * @return true if the number is prime, false otherwise
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
     * Method determines which numbers in a given range are prime.
     *
     * @param limit the upper limit of the range to check
     * @return an array storing results [0,1] indicating whether each number is prime
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
     * Method finds the greatest common divisor (GCD) of two numbers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b
     * @throws ArithmeticException if b equals 0
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
     * Method communicates with the user and reads the selected method and its arguments.
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