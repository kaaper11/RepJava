package pd16;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class GameService {
    private static Set<Game> games = new HashSet<>();

    public static void addGame(Game game) {
        if (game.getName() == null || game.getName().isBlank()) {
            throw new ValidationException("Nazwa gry nie może być pusta!");
        } else if (game.getRentalPrice() == null || game.getRentalPrice().compareTo(new BigDecimal("0")) <= 0) {
            throw new ValidationException("Cena nie może być mniejsza lub równa 0!");
        } else if (games.contains(game)) {
            throw new ValidationException("Gra już znajduje się w systemie!");
        } else {
            games.add(game);
            System.out.println("Dodano gre.");
        }
    }

    public static Optional<Game> getGame(String gameName) {
        return games.stream()
                .filter(game -> game.getName().equals(gameName))
                .findAny();
    }

    public static void getAllGames() {
        games.stream().forEach(System.out::println);
    }

    public static List<Game> rentedGames() {
        return games.stream()
                .filter(game -> game.getStatus().equals(Status.RENT))
                .collect(Collectors.toList());
    }

    public static List<Game> getTopGames() {
        return games.stream()
                .filter(game -> game.getNumberOfRentals() >= 2)
                .collect(Collectors.toList());
    }

    public static Map<Category, List<Game>> getGamesByCategory() {
        return games.stream()
                .collect(Collectors.groupingBy(Game::getCategory));
    }

    public static List<Game> getNeverRentedGames() {
        return games.stream()
                .filter(game -> game.getNumberOfRentals() == 0)
                .collect(Collectors.toList());
    }

    public static List<Game> getSortedGamesByRentalPrice() {
        return games.stream()
                .sorted(Comparator.comparing(Game::getRentalPrice))
                .toList();
    }

    public static List<Game> getSortedGamesByRentedCount() {
        return games.stream()
                .sorted(Comparator.comparing(Game::getNumberOfRentals))
                .toList();
    }

    public static List<Game> getSortedGamesByName() {
        return games.stream()
                .sorted(Comparator.comparing(Game::getName))
                .toList();
    }
}
