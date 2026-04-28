package pd16;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@ToString
@Getter
public class Game {
    private final String name;
    private final Category category;
    private final BigDecimal rentalPrice;
    private Status status;
    private int numberOfRentals = 0;

    private Game(String name, Category category, BigDecimal rentalPrice) {
        this.name = name;
        this.category = category;
        this.rentalPrice = rentalPrice;
        this.status = Status.AVAILABLE;
    }

    public static Game of(String name, Category category, BigDecimal rentalPrice) {
        return new Game(name, category, rentalPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public void rent() {
        if (status == Status.RENTED) {
            throw new RentException(Status.RENTED);
        }
        numberOfRentals++;
        status = Status.RENTED;
    }

    public void returnGame() {
        if (status == Status.AVAILABLE) {
            throw new RentException(Status.AVAILABLE);
        }
        status = Status.AVAILABLE;
    }
}
