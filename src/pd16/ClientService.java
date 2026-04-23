package pd16;

import java.util.*;

public class ClientService {
    private static Set<Client> clients = new HashSet<>();

    public static void addClient(Client client) {
        if (!clients.contains(client) && !client.getName().isBlank()) {
            clients.add(client);
            System.out.println("Dodano klienta.");
        } else {
            throw new ValidationException("Sprawdź dane i spóbuj ponowanie.");
        }
    }

    public static Optional<Client> getClient(String mail) {
        return clients.stream()
                .filter(client -> client.getMail().equals(mail))
                .findAny();
    }

    public static void getAllClients() {
        clients.stream().forEach(System.out::println);
    }

    public static Client getTopRentClient() {
        return clients.stream()
                .max(Comparator.comparingInt(Client::getNumberOfActiveRentals))
                .orElseThrow(() -> new NotFoundException("klientów"));
    }
}
