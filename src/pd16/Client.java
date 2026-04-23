package pd16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@ToString
@Getter
public class Client {
    private final String name;
    private final String mail;
    private int numberOfActiveRentals = 0;

    private Client(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public static Client of(String name, String mail) {
        return new Client(name, mail);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(mail, client.mail);
    }

    public void rentGame() {
        numberOfActiveRentals++;
    }

    public void returnGame() {
        numberOfActiveRentals--;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
