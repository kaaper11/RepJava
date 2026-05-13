package pd15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
public class CategoryStatistics {
    private BigDecimal totalRevenue;
    private BigDecimal averagePrice;
}
