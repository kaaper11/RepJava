package pd10;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(staticName = "initialize")
public final class TripCatalog {
    private final List<Trip> trips = new ArrayList<>();

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public Optional<Trip> findByDestination(String destinationName) {
        return Optional.ofNullable(destinationName)
                .flatMap(name -> trips
                        .stream()
                        .filter(trip -> trip.getDestination().name().equals(destinationName))
                        .findFirst());
    }

    public Optional<Trip> findBestTrip(User user) {
        return Optional.ofNullable(user.getPreferredTransport())
                .flatMap(transportType -> trips
                        .stream()
                        .filter(trip -> trip.getTransport().equals(user.getPreferredTransport()))
                        .findFirst())
                .or(() -> Optional.ofNullable(user.getBudget())
                        .flatMap(budget -> trips
                                .stream()
                                .filter(trip -> trip.getPrice().compareTo(user.getBudget()) <= 0)
                                .findFirst()));
    }

    public String buildTripDescription(User user) {
        return findBestTrip(user)
                .map(trip -> trip.toString())
                .orElse("No trip available");
    }
}
