package pd15;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductAnalytics {
    private static List<Product> products = new ArrayList<>();

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static List<Product> getTop5ProductsByRevenue() {
        return products.stream()
                .sorted(Comparator.comparingDouble(u -> u.getPrice().doubleValue() * u.getRating()))
                .limit(5)
                .toList();
    }

    public static Optional<Category> findCategoryWithHighestAverageRating() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet().stream()
                .max(Comparator.comparingDouble(entry -> entry.getValue().stream()
                        .collect(Collectors.summingDouble(Product::getRating))
                ))
                .map(entry -> entry.getKey());
    }

    public static List<Product> getLowStockProducts() {
        return products.stream()
                .filter(product -> product.getStock() < 10)
                .sorted(Comparator.comparingInt(Product::getStock))
                .toList();
    }

    public static Map<Category, Integer> getCountCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().size()));
    }

    public static Map<Category, CategoryStatistics> getCategoryFinancialStats() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream().collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> {
                            BigDecimal sum = entry.getValue().stream()
                                    .map(Product::getPrice)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                            BigDecimal avg = sum.divide(new BigDecimal(entry.getValue().size()));

                            return new CategoryStatistics(sum, avg);
                        }
                ));
    }

    public static List<Product> getHighlyRatedAffordableProducts() {
        return products.stream()
                .filter(product -> product.getRating() >= 4.5 && product.getPrice().compareTo(new BigDecimal("500")) == -1)
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    public static boolean allProductsHavePositiveRating() {
        return products.stream()
                .allMatch(product -> product.getRating() > 0);
    }

    public static Optional<Product> findMostExpensiveProduct() {
        return products.stream().max(Comparator.comparing(Product::getPrice));
    }

    public static Map<String, Long> partitionAndCountByPrice() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.getPrice().compareTo(new BigDecimal("1000")) == 1 ? "premium" : "standard",
                        Collectors.counting()
                ));
    }

    public static List<String> generateTopCategoryProductReport() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet().stream()
                .map(entry -> "Kategoria: " + entry.getKey() + " ,top produkt: " + entry.getValue().stream().max(Comparator.comparing(Product::getSold)).get())
                .toList();
    }
}
