package pd04;

public class Main2 {
    public static void main(String[] args) {

        int j;
        int licznik;
        for (int i = 2; i <= 50; i++) {
            licznik = 0;
            j = 1;
            while (i >= j) {
                if (licznik > 2){
                    break;
                }
                if (i % j == 0) {
                    licznik++;
                }
                j++;
            }
            if (licznik == 2) {
                System.out.print(i + " ");
            }
        }
    }
}
