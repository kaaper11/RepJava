package pd16.entity;

import lombok.*;

@ToString
@RequiredArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
public class Rental {
    private final Game game;
    private final Client client;
    private RentalStatus status = RentalStatus.ACTIVE;

    public void returnGame() {
        this.status = RentalStatus.INACTIVE;
    }

}
