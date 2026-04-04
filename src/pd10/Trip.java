package pd10;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
public final class Trip {
    private final Destination destination;
    private final TransportType transport;
    private final double price;
    private final int duration;
}
