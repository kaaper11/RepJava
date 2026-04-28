package pd16;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Client {
    private final String name;
    private final String email;
    private int numberOfActiveRentals = 0;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(email, client.email);
    }

    public void rentGame() {
        numberOfActiveRentals++;
    }

    public void returnGame() {
        numberOfActiveRentals--;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
