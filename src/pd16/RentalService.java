package pd16;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalService {
    private static RentalRepository rentalRepository = new RentalRepository();

    public static void rentGame(String gameName, String clientMail) {
        Game game = GameService.getGame(gameName).orElseThrow(() -> new NotFoundException(gameName));
        Client client = ClientService.getClient(clientMail).orElseThrow(() -> new NotFoundException(clientMail));

        game.rent();
        client.rentGame();

        rentalRepository.safe(game, client);

        System.out.println("Dodano rezerwacje klienta: " + clientMail + ", na gre: " + gameName + ".");
    }

    public static void returnGame(String gameName, String clientMail) {
        Game game = GameService.getGame(gameName).orElseThrow(() -> new NotFoundException(gameName));
        Client client = ClientService.getClient(clientMail).orElseThrow(() -> new NotFoundException(clientMail));
        Rental rental = rentalRepository.getRental(game, client).orElseThrow(() -> new NotFoundException("wypożyczenie"));

        rental.returnGame();
        game.returnGame();
        client.returnGame();

        System.out.println("Zakończono wypożyczenie: " + clientMail + ", na gre: " + gameName + ".");
    }

    public static List<Rental> clientRentals(String clientMail) {
        return rentalRepository.getRentals().stream()
                .filter(rental -> rental.getStatus().equals(RentalStatus.ACTIVE))
                .filter(rental -> rental.getClient().getEmail().equals(clientMail))
                .collect(Collectors.toList());
    }

    public static Map<Category, BigDecimal> calculateRevenueByCategory() {
        return rentalRepository.getRentals().stream()
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
