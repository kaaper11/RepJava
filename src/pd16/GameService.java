package pd16;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class GameService {
    private static GameRepository gameRepository = new GameRepository();

    public static void addGame(Game game) {
        Validator.gameValidateName(game);
        Validator.gameValidateRentalPrice(game);
        Validator.gameValidateContains(gameRepository, game);
        gameRepository.safe(game);
        System.out.println("Dodano gre.");
    }

    public static Optional<Game> getGame(String gameName) {
        return gameRepository.getGame(gameName);
    }

    public static void getAllGames() {
        gameRepository.getAllGames().stream().forEach(System.out::println);
    }

    public static List<Game> rentedGames() {
        return gameRepository.getAllGames().stream()
                .filter(game -> game.getStatus().equals(Status.RENTED))
                .collect(Collectors.toList());
    }

    public static List<Game> getTopGames() {
        return gameRepository.getAllGames().stream()
                .filter(game -> game.getNumberOfRentals() >= 2)
                .collect(Collectors.toList());
    }

    public static Map<Category, List<Game>> getGamesByCategory() {
        return gameRepository.getAllGames().stream()
                .collect(Collectors.groupingBy(Game::getCategory));
    }

    public static List<Game> getNeverRentedGames() {
        return gameRepository.getAllGames().stream()
                .filter(game -> game.getNumberOfRentals() == 0)
                .collect(Collectors.toList());
    }

    public static List<Game> getSortedGamesByRentalPrice() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getRentalPrice))
                .toList();
    }

    public static List<Game> getSortedGamesByRentedCount() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getNumberOfRentals))
                .toList();
    }

    public static List<Game> getSortedGamesByName() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getName))
                .toList();
    }
}
