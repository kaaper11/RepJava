package pd20.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRaport {
    private User user;
    private Weather weather;

    public UserRaport() {
    }
}
