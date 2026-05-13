package pd17;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SalesAnalytics {
    private List<Product> products;

    public SalesAnalytics(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalRevenue() {
        return products.stream()
                .map(Product::getRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Product> top3Products() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getRevenue).reversed())
                .limit(3)
                .toList();

    }

    private long getOneCategorySize(Category category) {
        return products.stream()
                .filter(product -> product.category() == category)
                .count();
    }

    private BigDecimal getOneCategoryMean(Category category) {
        return products.stream()
                .filter(product -> product.category() == category)
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(getOneCategorySize(category)), 2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getOneCategoryRevenue(Category category) {
        return products.stream()
                .filter(product -> product.category() == category)
                .map(Product::getRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CategorySummary> getCategorySummary() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category))
                .entrySet().stream()
                .map(entry -> {
                    Category category = entry.getKey();
                    BigDecimal mean = getOneCategoryMean(category);
                    BigDecimal revenue = getOneCategoryRevenue(category);
                    return new CategorySummary(category, mean, revenue);
                })
                .sorted(Comparator.comparing(CategorySummary::mean))
                .toList();
    }
}
