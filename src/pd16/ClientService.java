package pd16;

import java.util.*;

public class ClientService {
    private static ClientRepository clientRepository = new ClientRepository();

    public static void addClient(Client client) {
        Validator.clientNameValidation(client);
        Validator.clientValidateContains(client, clientRepository);
        clientRepository.safe(client);
        System.out.println("Dodano klienta.");
    }

    public static Optional<Client> getClient(String mail) {
        return clientRepository.getClient(mail);
    }

    public static void getAllClients() {
       clientRepository.getAllClients().stream().forEach(System.out::println);
    }

    public static Client getTopRentClient() {
        return clientRepository.getAllClients().stream()
                .max(Comparator.comparingInt(Client::getNumberOfActiveRentals))
                .orElseThrow(() -> new NotFoundException("klientów"));
    }
}
