package pd10;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public final class User {
    private final String name;
    private final TransportType preferredTransport;
    private final Double budget;
}
