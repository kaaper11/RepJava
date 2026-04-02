package pd07;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComputerGame extends RentableResource {
    private final String gameGenre;

    public ComputerGame(String id, String name, BigDecimal basePrice, String gameGenre) {
        super(id, name, basePrice, ResourceType.COMPUTER_GAME);
        this.gameGenre = gameGenre;
    }

    @Override
    public BigDecimal calculatePrice(int days) {
        return (getBasePrice().multiply(new BigDecimal(days))).multiply(new BigDecimal("1.2"));
    }

    @Override
    public String toString() {
        return super.toString() + " " + gameGenre;
    }
}
