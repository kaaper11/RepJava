package pd07;

import lombok.Data;

@Data
public class ComputerGame extends Resource {
    private final String gameGenre;

    public ComputerGame(String id, String name, double basePrice, ResourceType type, String gameGenre) {
        super(id, name, basePrice, type);
        this.gameGenre = gameGenre;
    }

    @Override
    public double calculatePrice(int days) {
        return days * getBasePrice() * 1.2;
    }

    @Override
    public String toString() {
        return super.toString() + " " + gameGenre;
    }
}
