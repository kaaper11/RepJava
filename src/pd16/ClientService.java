package pd16;

import java.util.*;

public class ClientService {
    private ClientRepository clientRepository;
    private ClientsValidator clientsValidator;

    public ClientService(ClientRepository clientRepository, ClientsValidator clientsValidator) {
        this.clientRepository = clientRepository;
        this.clientsValidator = clientsValidator;
    }

    public void addClient(Client client) {
        clientsValidator.clientNameValidation(client);
        clientsValidator.clientValidateContains(client, clientRepository);
        clientRepository.save(client);
        System.out.println("Dodano klienta.");
    }

    public Optional<Client> getClient(String mail) {
        return clientRepository.getClient(mail);
    }

    public void getAllClients() {
       clientRepository.getAllClients().stream().forEach(System.out::println);
    }

    public Client getTopRentClient() {
        return clientRepository.getAllClients().stream()
                .max(Comparator.comparingInt(Client::getNumberOfActiveRentals))
                .orElseThrow(() -> new NotFoundException("klientów"));
    }
}
