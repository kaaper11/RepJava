package pd13;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;

@Getter
public class Warehouse {
    private Map<Long, Product> inventory = new HashMap<>();
    private Map<String, Set<Product>> byCategory = new HashMap<>();
    private PriorityQueue<Product> lowStock = new PriorityQueue<>(Comparator.comparing(Product::getQuantity));

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);

        if (byCategory.containsKey(product.getCategory())) {
            byCategory.get(product.getCategory()).add(product);
        } else {
            Set<Product> set = new HashSet<>();
            set.add(product);
            byCategory.put(product.getCategory(), set);
        }

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
        Map<String, DoubleSummaryStatistics> productStatisticMap = new HashMap<>();

        for (String category : byCategory.keySet()) {
            DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
            for (Product product : byCategory.get(category)) {
                statistics.accept(product.getPrice().doubleValue());
            }
            productStatisticMap.put(category, statistics);
        }
        return productStatisticMap;
    }

    public TreeMap<BigDecimal, List<Product>> exportSortedByPrice() {
        TreeMap<BigDecimal, List<Product>> decimalListMap = new TreeMap<>();

        for (Product product : inventory.values()) {
            if (!decimalListMap.containsKey(product.getPrice())) {
                ArrayList<Product> products = new ArrayList<>();
                products.add(product);
                decimalListMap.put(product.getPrice(), products);

            } else {
                decimalListMap.get(product.getPrice()).add(product);
            }
        }
        return decimalListMap;
    }
}
