package pd16.validator;

import pd16.entity.Client;
import pd16.repository.ClientRepository;
import pd16.exception.ValidationException;

public class ClientsValidator {
    public void clientNameValidation(Client client) {
        if (client.getName().isBlank() || client.getName() == null) {
            throw new ValidationException("Sprawdź dane i spóbuj ponowanie.");
        }
    }

    public void clientValidateContains(Client client, ClientRepository clientRepository) {
        if (clientRepository.getAllClients().contains(client)){
            throw new ValidationException("Klient znjaduje się już w bazie danych");
        }
    }
}
