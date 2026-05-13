package zl11;

import lombok.Getter;

@Getter
public class LaptopStore {
    private int availableQuantity = 10;

    public synchronized OrderStats buyLaptop(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
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
