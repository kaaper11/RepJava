package pd16;

import java.math.BigDecimal;

public class Validator {

    public static void gameValidateName(Game game) {
        if (game.getName() == null || game.getName().isBlank()) {
            throw new ValidationException("Nazwa gry nie może być pusta!");
        }
    }

    public static void gameValidateRentalPrice(Game game) {
        if (game.getRentalPrice() == null || game.getRentalPrice().compareTo(new BigDecimal("0")) <= 0) {
            throw new ValidationException("Cena nie może być mniejsza lub równa 0!");
        }
    }

    public static void gameValidateContains(GameRepository gameRepository, Game game) {
        if (gameRepository.getAllGames().contains(game)) {
            throw new ValidationException("Gra już znajduje się w systemie!");
        }
    }

    public static void clientNameValidation(Client client) {
        if (client.getName().isBlank() || client.getName() == null) {
            throw new ValidationException("Sprawdź dane i spóbuj ponowanie.");
        }
    }

    public static void clientValidateContains(Client client, ClientRepository clientRepository) {
        if (clientRepository.getAllClients().contains(client)){
            throw new ValidationException("Klient znjaduje się już w bazie danych");
        }
    }
}
