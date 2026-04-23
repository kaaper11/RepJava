package pd16;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
public class Rental {
    private final Game game;
    private final Client client;
}
