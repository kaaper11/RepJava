package zl11;

public class LaptopStore {
    private int availableQuantity = 10;

    public synchronized OrderStats buyLaptop(String clientName, int quantity) {
        if (availableQuantity < quantity) {
            System.out.println("Brak produktu w sklepie");
            return new OrderStats(false, 0);
        } else {
            availableQuantity -= quantity;
            System.out.println("Zakupiono " + quantity + " laptopów.");
            return new OrderStats(true, quantity);
        }
    }
}
