package pd10;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public final class User {
    private final String name;
    private final TransportType preferredTransport;
    private final BigDecimal budget;
}
