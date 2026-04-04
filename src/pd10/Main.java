package pd10;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Trip trip1 = Trip.of(new Destination("Ibiza", "Hiszpania"), TransportType.PLANE, 2000, 14);
        Trip trip2 = Trip.of(new Destination("Mediolan", "Włochy"), TransportType.CAR, 1000, 10);
        Trip trip3 = Trip.of(new Destination("Zakopane", "Polska"), TransportType.TRAIN, 800, 7);

        User user1 = User.of("Kamil", null, null);
        User user2 = User.of("Karol", TransportType.CAR, Double.valueOf(1000));
        User user3 = User.of("Konrad", null, Double.valueOf(2000));

        TripCatalog tripCatalog = TripCatalog.of();
        tripCatalog.addTrip(trip1);
        tripCatalog.addTrip(trip2);
        tripCatalog.addTrip(trip3);

        Optional<Trip> optionalTrip = tripCatalog.findByDestination("Ibiza");
        if (optionalTrip.isPresent()) {
            System.out.println(optionalTrip.get());
        } else {
            System.out.println("No trip found");
        }

        System.out.println(tripCatalog.buildTripDescription(user2));
    }
}
