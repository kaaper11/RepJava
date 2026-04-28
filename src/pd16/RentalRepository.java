package pd16;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalRepository {
    private static List<Rental> rentals = new ArrayList<>();

    public void safe(Game game, Client client){
        rentals.add(Rental.of(game, client));
    }

    public Optional<Rental> getRental(Game game, Client client) {
        return rentals.stream()
                .filter(rental -> rental.equals(Rental.of(game, client)))
                .findFirst();
    }

    public List<Rental> getRentals() {
        return rentals;
    }

}
