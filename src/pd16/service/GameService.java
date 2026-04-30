package pd16.service;

import pd16.entity.Category;
import pd16.entity.Game;
import pd16.validator.GamesValidator;
import pd16.repository.GameRepository;
import pd16.entity.Status;

import java.util.*;
import java.util.stream.Collectors;

public class GameService {
    private GameRepository gameRepository;
    private GamesValidator gamesValidator;

    public GameService(GameRepository gameRepository, GamesValidator gamesValidator) {
        this.gameRepository = gameRepository;
        this.gamesValidator = gamesValidator;
    }

    public void addGame(Game game) {
        gamesValidator.gameValidateName(game);
        gamesValidator.gameValidateRentalPrice(game);
        gamesValidator.gameValidateContains(gameRepository, game);
        gameRepository.save(game);
        System.out.println("Dodano gre.");
    }

    public Optional<Game> getGame(String gameName) {
        return gameRepository.getGame(gameName);
    }

    public void getAllGames() {
        gameRepository.getAllGames().stream().forEach(System.out::println);
    }

    public List<Game> rentedGames() {
        return gameRepository.getAllGames().stream()
                .filter(game -> game.getStatus().equals(Status.RENTED))
                .collect(Collectors.toList());
    }

    public List<Game> getTopGames() {
        return gameRepository.getAllGames().stream()
                .filter(game -> game.getNumberOfRentals() >= 2)
                .collect(Collectors.toList());
    }

    public Map<Category, List<Game>> getGamesByCategory() {
        return gameRepository.getAllGames().stream()
                .collect(Collectors.groupingBy(Game::getCategory));
    }

    public List<Game> getNeverRentedGames() {
        return gameRepository.getAllGames().stream()
                .filter(game -> game.getNumberOfRentals() == 0)
                .collect(Collectors.toList());
    }

    public List<Game> getSortedGamesByRentalPrice() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getRentalPrice))
                .toList();
    }

    public List<Game> getSortedGamesByRentedCount() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getNumberOfRentals))
                .toList();
    }

    public List<Game> getSortedGamesByName() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getName))
                .toList();
    }
}
