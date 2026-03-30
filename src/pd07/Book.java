package pd07;

import lombok.Data;

@Data
public class Book extends RentableResource {
    private final String author;

    public Book(String id, String name, double basePrice, String author) {
        super(id, name, basePrice, ResourceType.BOOK);
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
