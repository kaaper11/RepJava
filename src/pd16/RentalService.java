package pd16;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalService {
    private static List<Rental> allRentals = new ArrayList<>();
    private static List<Rental> activeRentals = new ArrayList<>();

    public static void rentGame(String gameName, String clientMail) {
        Game game = GameService.getGame(gameName).orElseThrow(() -> new NotFoundException(gameName));
        Client client = ClientService.getClient(clientMail).orElseThrow(() -> new NotFoundException(clientMail));

        game.rent();
        client.rentGame();

        activeRentals.add(Rental.of(game, client));
        allRentals.add(Rental.of(game, client));

        System.out.println("Dodano rezerwacje klienta: " + clientMail + ", na gre: " + gameName + ".");
    }

    public static void returnGame(String gameName, String clientMail) {
        Game game = GameService.getGame(gameName).orElseThrow(() -> new NotFoundException(gameName));
        Client client = ClientService.getClient(clientMail).orElseThrow(() -> new NotFoundException(clientMail));

        activeRentals.remove(activeRentals.stream()
                .filter(rental -> rental.equals(Rental.of(game, client)))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("wypożyczenia")));

        game.returnGame();
        client.returnGame();

        System.out.println("Zakończono wypożyczenie: " + clientMail + ", na gre: " + gameName + ".");
    }

    public static List<Rental> clientRentals(String clientMail) {
        return activeRentals.stream()
                .filter(rental -> rental.getClient().getMail().equals(clientMail))
                .collect(Collectors.toList());
    }

    public static Map<Category, BigDecimal> calculateRevenueByCategory() {
        return allRentals.stream()
                .collect(Collectors.groupingBy(rental -> rental.getGame().getCategory()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream()
                                .map(rental -> rental.getGame().getRentalPrice())
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ));
    }
}
