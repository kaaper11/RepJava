package pd04;

public class Main3 {
    public static void main(String[] args) {
        int iloscSpacji;

        for (int k = 0; k < 5; k++) {
            iloscSpacji = 0;
            for (int l = 0; l < 5 - k - 1; l++) {
                System.out.print(" ");
                iloscSpacji++;
            }
            for (int l = 0; l < 5 - iloscSpacji + k; l++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
