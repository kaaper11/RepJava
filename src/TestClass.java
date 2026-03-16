import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj wagę w kg");
        double wagaKg = sc.nextDouble();
        System.out.println("Podaj wzrost w cm");
        double wzrostCm = sc.nextDouble();
        double BMI = wagaKg / ((wzrostCm / 100.0) * (wzrostCm / 100.0));

        if (wzrostCm < 30 || wzrostCm > 250) {
            System.out.println("nieprawidłowy wzrost");
        } else {
            System.out.println("BMI: " + Math.round(BMI * 100.0) / 100.0);
            if (BMI < 18.5) {
                System.out.println("Niedowaga");
            } else if (BMI >= 18.5 && BMI <= 24.9) {
                System.out.println("Norma");
            } else if (BMI >= 25 && BMI <= 29.9) {
                System.out.println("Nadwaga");
            } else {
                System.out.println("Otyłość");
            }
        }


    }
}
