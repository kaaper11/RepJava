package pd16;

import java.math.BigDecimal;

public class GamesValidator {

    public void gameValidateName(Game game) {
        if (game.getName() == null || game.getName().isBlank()) {
            throw new ValidationException("Nazwa gry nie może być pusta!");
        }
    }

    public void gameValidateRentalPrice(Game game) {
        if (game.getRentalPrice() == null || game.getRentalPrice().compareTo(new BigDecimal("0")) <= 0) {
            throw new ValidationException("Cena nie może być mniejsza lub równa 0!");
        }
    }

    public void gameValidateContains(GameRepository gameRepository, Game game) {
        if (gameRepository.getAllGames().contains(game)) {
            throw new ValidationException("Gra już znajduje się w systemie!");
        }
    }


}
