package pd14;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class Event {
    private String name;
    private int numberOfParticipants;
    private TypeOfEvent type;
}
