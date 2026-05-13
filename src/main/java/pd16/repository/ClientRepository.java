package pd16.repository;

import pd16.entity.Client;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientRepository {
    private Set<Client> clients = new HashSet<>();

    public void save(Client client) {
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
