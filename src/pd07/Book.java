package pd07;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Book extends Resource {
    private final String author;

    public Book(String id, String name, double basePrice, ResourceType type, String author) {
        super(id, name, basePrice, type);
        this.author = author;
    }

    @Override
    public double calculatePrice(int days) {
        return days * getBasePrice();
    }

    @Override
    public String toString() {
        return super.toString() + " " + author;
    }
}
