package zl07;

import java.util.List;

public class Zl07 {
    public static void main(String[] args) {
        List<Product> products = List.of(
                Electronics.create("pralka", 1000, "agd", 24),
                FoodProduct.create("banan", 3, "owoce", 7),
                Electronics.create("telewizor", 1200, "rtv", 12),
                FoodProduct.create("baton", 7, "słodycze", 100),
                Electronics.create("telefon", 3200, "mobilne", 36)
        );

        int countElectronics = 0;

        for (Product product : products) {
            System.out.println(product.getDescription());
            if (product instanceof Electronics) {
                countElectronics++;
            }
        }

        System.out.println(countElectronics);
    }
}
