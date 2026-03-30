package pd07;

import lombok.Getter;

@Getter
public abstract class RentableResource implements Comparable<RentableResource> {

    private static int resourceCount;
    private final String id;
    private final String name;
    private double basePrice;
    private final ResourceType type;

    public RentableResource(String id, String name, double basePrice, ResourceType type) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.type = type;
        resourceCount++;
    }

    @Override
    public int compareTo(RentableResource o) {
        return Double.compare(this.basePrice, o.basePrice);
    }

    @Override
    public String toString() {
        return "Zasób: " + id + ", " + name + ", " + basePrice + ", " + type + ",";
    }

    public abstract double calculatePrice(int days);
}
