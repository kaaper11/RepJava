package pd13;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Warehouse {
    private Map<Long, Product> inventory = new HashMap<>();
    private Map<String, Set<Product>> byCategory = new HashMap<>();
    private PriorityQueue<Product> lowStock = new PriorityQueue<>(Comparator.comparing(Product::getQuantity));

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);

        byCategory.computeIfAbsent(product.getCategory(), k -> new HashSet<>()).add(product);

        if (product.getQuantity() < 5) {
            lowStock.offer(product);
        }
    }

    public void removeProduct(Product product) {
        inventory.remove(product.getId());

        byCategory.get(product.getCategory()).remove(product);

        lowStock.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        inventory.get(product.getId()).setQuantity(quantity);

        byCategory.get(product.getCategory()).remove(product);
        byCategory.get(product.getCategory()).add(new Product(product.getId(), product.getName(), product.getCategory(), product.getPrice(), quantity));

        lowStock.remove(product);
        if (quantity < 5) {
            lowStock.offer(new Product(product.getId(), product.getName(), product.getCategory(), product.getPrice(), quantity));
        }
    }

    public List<Product> findByCategory(String category) {
        List<Product> products = new ArrayList<>();
        for (Product product : inventory.values()) {
            if (product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        products.sort(Comparator.comparing(Product::getQuantity).reversed());
        return products;
    }

    public List<Product> getLowStockReport() {
        List<Product> products = new ArrayList<>();

        for (Product product : lowStock) {
            products.add(product);
        }
        products.sort(Comparator.comparing(Product::getQuantity));
        return products;
    }

    public Map<String, DoubleSummaryStatistics> getCategoryStatistics() {
        return byCategory.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()))
                ));
    }

    public Map<BigDecimal, List<Product>> exportSortedByPrice() {
        return inventory.values().stream()
                .collect(Collectors.groupingBy(
                        Product::getPrice,
                        Collectors.toList()
                ));
    }
}
