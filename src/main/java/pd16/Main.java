package pd16;

import pd16.repository.ClientRepository;
import pd16.repository.GameRepository;
import pd16.repository.RentalRepository;
import pd16.entity.Category;
import pd16.entity.Client;
import pd16.entity.Game;
import pd16.exception.NotFoundException;
import pd16.exception.RentException;
import pd16.exception.ValidationException;
import pd16.service.ClientService;
import pd16.service.GameService;
import pd16.service.RentalService;
import pd16.validator.ClientsValidator;
import pd16.validator.GamesValidator;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService(new ClientRepository(), new ClientsValidator());
        GameService gameService = new GameService(new GameRepository(), new GamesValidator());
        RentalService rentalService = new RentalService(new RentalRepository(), gameService, clientService);

        try {
            clientService.addClient(Client.of("Wojtek", "wojtek@gmail.com"));
            clientService.addClient(Client.of("Kamil", "kamil@gmail.com"));
            clientService.addClient(Client.of("Robert", "robert@gmail.com"));

            gameService.addGame(Game.of("gra1", Category.RPG, new BigDecimal("10")));
            gameService.addGame(Game.of("gra2", Category.CARDS, new BigDecimal("5")));
            gameService.addGame(Game.of("gra3", Category.SCIENCE_FICTION, new BigDecimal("15")));
            gameService.addGame(Game.of("gra4", Category.STRATEGY, new BigDecimal("15")));
            gameService.addGame(Game.of("gra5", Category.FANTASY, new BigDecimal("90")));
            gameService.addGame(Game.of("gra6", Category.STRATEGY, new BigDecimal("20")));
            gameService.addGame(Game.of("gra7", Category.FANTASY, new BigDecimal("30")));

            rentalService.rentGame("gra1", "wojtek@gmail.com");
            rentalService.rentGame("gra2", "wojtek@gmail.com");
            rentalService.rentGame("gra3", "wojtek@gmail.com");

            rentalService.rentGame("gra4", "kamil@gmail.com");
            rentalService.rentGame("gra5", "kamil@gmail.com");

            rentalService.rentGame("gra6", "robert@gmail.com");
            rentalService.returnGame("gra6", "robert@gmail.com");
            rentalService.rentGame("gra6", "robert@gmail.com");

            //metody
            //rents
            System.out.println(rentalService.calculateRevenueByCategory());
            System.out.println(rentalService.clientRentals("wojtek@gmail.com"));

            //clients
            clientService.getAllClients();
            System.out.println(clientService.getTopRentClient());

            //games
            gameService.getAllGames();
            System.out.println(gameService.rentedGames());
            System.out.println(gameService.getTopGames());
            System.out.println(gameService.getGamesByCategory());
            System.out.println(gameService.getNeverRentedGames());
            System.out.println(gameService.getSortedGamesByName());
            System.out.println(gameService.getSortedGamesByRentalPrice());
            System.out.println(gameService.getSortedGamesByRentedCount());

        } catch (ValidationException | RentException | NotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
