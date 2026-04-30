package pd16.repository;

import pd16.entity.Game;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameRepository {
    private static Set<Game> games = new HashSet<>();

    public void save(Game game) {
        games.add(game);
    }

    public Set<Game> getAllGames() {
        return games;
    }

    public Optional<Game> getGame(String name) {
        return games.stream()
                .filter(game -> game.getName().equals(name))
                .findAny();
    }
}
