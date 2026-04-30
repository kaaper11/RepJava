package pd16.service;

import pd16.repository.RentalRepository;
import pd16.entity.Client;
import pd16.entity.Game;
import pd16.entity.Rental;
import pd16.entity.Category;
import pd16.entity.RentalStatus;
import pd16.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentalService {
    private RentalRepository rentalRepository;
    private GameService gameService;
    private ClientService clientService;

    public RentalService(RentalRepository rentalRepository, GameService gameService, ClientService clientService) {
        this.rentalRepository = rentalRepository;
        this.gameService = gameService;
        this.clientService = clientService;
    }

    public void rentGame(String gameName, String clientMail) {
        Game game = gameService.getGame(gameName).orElseThrow(() -> new NotFoundException(gameName));
        Client client = clientService.getClient(clientMail).orElseThrow(() -> new NotFoundException(clientMail));

        game.rent();
        client.rentGame();

        rentalRepository.save(game, client);

        System.out.println("Dodano rezerwacje klienta: " + clientMail + ", na gre: " + gameName + ".");
    }

    public void returnGame(String gameName, String clientMail) {
        Game game = gameService.getGame(gameName).orElseThrow(() -> new NotFoundException(gameName));
        Client client = clientService.getClient(clientMail).orElseThrow(() -> new NotFoundException(clientMail));
        Rental rental = rentalRepository.getRental(game, client).orElseThrow(() -> new NotFoundException("wypożyczenie"));

        rental.returnGame();
        game.returnGame();
        client.returnGame();

        System.out.println("Zakończono wypożyczenie: " + clientMail + ", na gre: " + gameName + ".");
    }

    public List<Rental> clientRentals(String clientMail) {
        return rentalRepository.getRentals().stream()
                .filter(rental -> rental.getStatus().equals(RentalStatus.ACTIVE))
                .filter(rental -> rental.getClient().getEmail().equals(clientMail))
                .collect(Collectors.toList());
    }

    public Map<Category, BigDecimal> calculateRevenueByCategory() {
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
