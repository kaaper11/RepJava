package pd07;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class RentableResource implements Comparable<RentableResource> {

    private static int resourceCount;
    private final String id;
    private final String name;
    private BigDecimal basePrice;
    private final ResourceType type;

    public RentableResource(String id, String name, BigDecimal basePrice, ResourceType type) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.type = type;
        resourceCount++;
    }

    @Override
    public int compareTo(RentableResource o) {
        return o.basePrice.compareTo(this.basePrice);
    }

    @Override
    public String toString() {
        return "Zasób: " + id + ", " + name + ", " + basePrice + ", " + type + ",";
    }

    public abstract BigDecimal calculatePrice(int days);
}
