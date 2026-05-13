package pd13;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@ToString
@AllArgsConstructor
public class Product implements Comparable<Product> {
    private final long id;
    private final String name;
    private final String category;
    private final BigDecimal price;
    @Setter
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
