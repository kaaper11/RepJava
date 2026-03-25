package pd06;

public class Pd03 {
    public static void main(String[] args) {
        try {
            MathLibrary.methodMenu();
        } catch (RuntimeException e) {
            System.out.println("Za każdym razem podawaj liczbe całkowitą!");
        }
    }
}
