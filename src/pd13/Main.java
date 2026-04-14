package pd13;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(1, "cos", "1", new BigDecimal("6"), 2);
        Product p2 = new Product(2, "cos", "1", new BigDecimal("6"), 5);
        Product p3 = new Product(3, "cos", "2", new BigDecimal("10"), 6);
        Product p4 = new Product(4, "cos", "3", new BigDecimal("10"), 2);
        Product p5 = new Product(5, "cos", "3", new BigDecimal("10"), 7);
        Product p6 = new Product(6, "cos", "2", new BigDecimal("10"), 4);
        Product p7 = new Product(7, "cos", "1", new BigDecimal("3"), 3);

        Warehouse w = new Warehouse();

        try {
            w.addProduct(p1);
            w.addProduct(p2);
            w.addProduct(p3);
            w.addProduct(p4);
            w.addProduct(p5);
            w.addProduct(p6);
            w.addProduct(p7);

            w.removeProduct(p2);
            w.updateQuantity(p1, 10);

            System.out.println(w.getInventory());
            System.out.println();
            System.out.println(w.getByCategory());
            System.out.println();
            System.out.println(w.getLowStock());
            System.out.println();
            System.out.println(w.findByCategory("1"));
            System.out.println();
            System.out.println(w.getLowStockReport());
            System.out.println();
            System.out.println(w.getCategoryStatistics());
            System.out.println();
            System.out.println(w.exportSortedByPrice());
        } catch (NullPointerException e) {
            System.err.println("Produkt nieprawidłowy, popraw i spróbuj ponowanie.");
        } catch (Exception e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}
