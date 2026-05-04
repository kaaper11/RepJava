package pd17;

import java.math.BigDecimal;

public record Product(String date, String id, String name, Category category, int quantity, BigDecimal price) {
    public BigDecimal getRevenue() {
        return price.multiply(new BigDecimal(quantity));
    }
}
