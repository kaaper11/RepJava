package pd17;

import java.math.BigDecimal;

public record CategorySummary(Category category, BigDecimal totalRevenue, BigDecimal mean) {
}
