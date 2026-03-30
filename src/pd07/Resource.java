package pd07;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class Resource implements Comparable<Resource> {

    public static int resourceCount;
    private final String id;
    private final String name;
    private double basePrice;
    private final ResourceType type;

    public Resource(String id, String name, double basePrice, ResourceType type) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.type = type;
        resourceCount++;
    }

    @Override
    public int compareTo(Resource o) {
        return Double.compare(this.basePrice, o.basePrice);
    }

    @Override
    public String toString() {
        return "Zasób: " + id + ", " + name + ", " + basePrice + ", " + type + ",";
    }

    public abstract double calculatePrice(int days);
}
