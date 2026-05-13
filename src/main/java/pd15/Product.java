package pd15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Product {
    private final long id;
    private final String name;
    private final Category category;
    private BigDecimal price;
    private int stock;
    private int sold;
    private double rating;
}
