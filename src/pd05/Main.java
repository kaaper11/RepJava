package pd05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static double[][] loadMatrix() {
        System.out.println("Podaj po kolei kolejne liczby macierzy.");

        double[][] matrix = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        return matrix;
    }

    static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.2f", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static double[][] transposedMatrix(double[][] matrix) {
        double[][] matrixTransposed = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrixTransposed.length; i++) {
            for (int j = 0; j < matrixTransposed[i].length; j++) {
                matrixTransposed[j][i] = matrix[i][j];
            }
        }
        return matrixTransposed;
    }

    static double[][] rotatedMatrix(double[][] matrix) {
        int indexToRotate;
        double[][] matrixRotated = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrixRotated.length; i++) {
            indexToRotate = 2;
            for (int j = 0; j < matrixRotated[i].length; j++) {
                matrixRotated[i][j] = matrix[indexToRotate][i];
                indexToRotate--;
            }
        }
        return matrixRotated;
    }

    static void sumMatrix(double[][] matrix) {
        double rowSum;
        double colSum;

        for (int i = 0; i < matrix.length; i++) {
            rowSum = 0.0;
            colSum = 0.0;

            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            System.out.printf("Suma %d wiersza: %.2f\n", i + 1, rowSum);
            System.out.printf("Suma %d kolumny: %.2f\n\n", i + 1, colSum);
        }
    }

    static boolean isMatrixSymmetric(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!Double.valueOf(matrix[i][j]).equals(Double.valueOf(matrix[j][i]))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            double[][] mainMatrix = loadMatrix();

            System.out.println("Macierz główna");
            printMatrix(mainMatrix);

            System.out.println("Macierz transponowana");
            printMatrix(transposedMatrix(mainMatrix));

            System.out.println("Macierz obrucona o 90 stopni");
            printMatrix(rotatedMatrix(mainMatrix));

            sumMatrix(mainMatrix);

            System.out.println("Czy macierz jest symetryczna? : " + isMatrixSymmetric(mainMatrix));

        } catch (InputMismatchException e) {
            System.out.println("Macierz przyjmuje tylko liczby!");
        }
    }
}
