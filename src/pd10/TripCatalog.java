package pd10;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

@NoArgsConstructor(staticName = "of")
public final class TripCatalog {
    private final ArrayList<Trip> trips = new ArrayList<>();

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public Optional<Trip> findByDestination(String destinationName) {
        return Optional.ofNullable(destinationName).isPresent()
                ? trips.stream()
                .filter(trip -> trip.getDestination().name().equals(destinationName))
                .findFirst()
                : Optional.empty();
    }

    public Optional<Trip> findBestTrip(User user) {
        return Optional.ofNullable(user.getPreferredTransport()).isPresent()
                ? trips.stream()
                .filter(trip -> trip.getTransport().equals(user.getPreferredTransport()))
                .findFirst()
                : Optional.ofNullable(user.getBudget()).isPresent()
                ? trips.stream()
                .filter(trip -> trip.getPrice() <= user.getBudget())
                .findFirst()
                : Optional.empty();
    }

    public String buildTripDescription(User user) {
        Optional<Trip> bestTrip = findBestTrip(user);
        return bestTrip.isPresent()
                ? bestTrip.get().toString()
                : "No trip available";
    }
}
