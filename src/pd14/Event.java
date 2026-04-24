package pd14;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class Event {
    private final String name;
    private final int numberOfParticipants;
    private final EventType type;
}
