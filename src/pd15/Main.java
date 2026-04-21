package pd15;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ProductAnalytics.addProduct(Product.of(1, "prod1", Category.FOOD, new BigDecimal("10"), 1, 100, 4.5));
        ProductAnalytics.addProduct(Product.of(2, "prod2", Category.CLOTHES, new BigDecimal("20"), 10, 1, 4));
        ProductAnalytics.addProduct(Product.of(3, "prod3", Category.ELECTRONICS, new BigDecimal("10100"), 20, 0, 4.2));
        ProductAnalytics.addProduct(Product.of(4, "prod4", Category.FURNITURE, new BigDecimal("500"), 5, 10, 3.4));
        ProductAnalytics.addProduct(Product.of(5, "prod6", Category.OTHER, new BigDecimal("180"), 100, 20, 5));

        System.out.println(ProductAnalytics.generateTopCategoryProductReport());
        System.out.println(ProductAnalytics.findCategoryWithHighestAverageRating());
        System.out.println(ProductAnalytics.getLowStockProducts());
        System.out.println(ProductAnalytics.getCountCategory());
        System.out.println(ProductAnalytics.getCategoryFinancialStats());
        System.out.println(ProductAnalytics.getHighlyRatedAffordableProducts());
        System.out.println(ProductAnalytics.allProductsHavePositiveRating());
        System.out.println(ProductAnalytics.findMostExpensiveProduct());
        System.out.println(ProductAnalytics.partitionAndCountByPrice());
        System.out.println(ProductAnalytics.generateTopCategoryProductReport());
    }
}
