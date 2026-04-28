package pd16;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientRepository {
    private static Set<Client> clients = new HashSet<>();

    public void safe(Client client) {
        clients.add(client);
    }

    public Optional<Client> getClient(String mail) {
        return clients.stream()
                .filter(client -> client.getEmail().equals(mail))
                .findAny();
    }

    public Set<Client> getAllClients() {
        return clients;
    }
}
