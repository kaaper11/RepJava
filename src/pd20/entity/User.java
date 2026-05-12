package pd20.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class User {
    private long id;
    private String name;
    private String email;
    private Address address;
    @Setter
    private List<Todo> todos;
    @Setter
    private List<Post> posts;

    public double getAddressLat() {
        return address.getGeo().getLat();
    }

    public double getAddressLng() {
        return address.getGeo().getLng();
    }
}
