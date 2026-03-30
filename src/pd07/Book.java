package pd07;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book extends RentableResource {
    private final String author;

    public Book(String id, String name, BigDecimal basePrice, String author) {
        super(id, name, basePrice, ResourceType.BOOK);
        this.author = author;
    }

    @Override
    public BigDecimal calculatePrice(int days) {
        return getBasePrice().multiply(new BigDecimal(days));
    }

    @Override
    public String toString() {
        return super.toString() + " " + author;
    }
}
