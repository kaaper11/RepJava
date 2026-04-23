package pd16;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            ClientService.addClient(Client.of("Wojtek", "wojtek@gmail.com"));
//            ClientService.addClient(Client.of("Wojciech", "wojtek@gmail.com"));
//            ClientService.addClient(Client.of("", "wojtek@gmail.com"));
            ClientService.addClient(Client.of("Kamil", "kamil@gmail.com"));
            ClientService.addClient(Client.of("Robert", "robert@gmail.com"));

            GameService.addGame(Game.of("gra1", Category.RPG, new BigDecimal("10")));
//            GameService.addGame(Game.of("gra1", Category.RPG, new BigDecimal("10")));
//            GameService.addGame(Game.of("", Category.RPG, new BigDecimal("10")));
//            GameService.addGame(Game.of("gra1111", Category.RPG, new BigDecimal("-10")));
            GameService.addGame(Game.of("gra2", Category.CARDS, new BigDecimal("5")));
            GameService.addGame(Game.of("gra3", Category.SCIENCE_FICTION, new BigDecimal("15")));
            GameService.addGame(Game.of("gra4", Category.STRATEGY, new BigDecimal("15")));
            GameService.addGame(Game.of("gra5", Category.FANTASY, new BigDecimal("90")));
            GameService.addGame(Game.of("gra6", Category.STRATEGY, new BigDecimal("20")));
            GameService.addGame(Game.of("gra7", Category.FANTASY, new BigDecimal("30")));

            RentalService.rentGame("gra1", "wojtek@gmail.com");
            RentalService.rentGame("gra2", "wojtek@gmail.com");
            RentalService.rentGame("gra3", "wojtek@gmail.com");

//            RentalService.rentGame("gra7", "nie ma mnie");
//            RentalService.rentGame("nie ma mnie", "kamil@gmail.com");

            RentalService.rentGame("gra4", "kamil@gmail.com");
            RentalService.rentGame("gra5", "kamil@gmail.com");

            RentalService.rentGame("gra6", "robert@gmail.com");
//            RentalService.rentGame("gra6", "robert@gmail.com");

            RentalService.returnGame("gra6", "robert@gmail.com");
            RentalService.rentGame("gra6", "robert@gmail.com");

            //metody
            //rents
            System.out.println(RentalService.calculateRevenueByCategory());
            System.out.println(RentalService.clientRentals("wojtek@gmail.com"));

            //clients
            ClientService.getAllClients();
            System.out.println(ClientService.getTopRentClient());

            //games
            GameService.getAllGames();
            System.out.println(GameService.rentedGames());
            System.out.println(GameService.getTopGames());
            System.out.println(GameService.getGamesByCategory());
            System.out.println(GameService.getNeverRentedGames());
            System.out.println(GameService.getSortedGamesByName());
            System.out.println(GameService.getSortedGamesByRentalPrice());
            System.out.println(GameService.getSortedGamesByRentedCount());

        } catch (ValidationException | RentException | NotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
