package pd10;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Trip trip1 = Trip.of(new Destination("Ibiza", "Hiszpania"), TransportType.PLANE, new BigDecimal("2000"), 14);
        Trip trip2 = Trip.of(new Destination("Mediolan", "Włochy"), TransportType.CAR, new BigDecimal("1000"), 10);
        Trip trip3 = Trip.of(new Destination("Zakopane", "Polska"), TransportType.TRAIN, new BigDecimal("800"), 7);

        User user1 = User.of("Kamil", null, null);
        User user2 = User.of("Karol", TransportType.CAR, new BigDecimal("1000"));
        User user3 = User.of("Konrad", null, new BigDecimal("2000"));

        TripCatalog tripCatalog = TripCatalog.initialize();
        tripCatalog.addTrip(trip1);
        tripCatalog.addTrip(trip2);
        tripCatalog.addTrip(trip3);

        System.out.println(tripCatalog.findByDestination("Ibiza")
                .map(trip -> trip.toString())
                .orElse("No trip found"));

        System.out.println(tripCatalog.buildTripDescription(user1));
    }
}
