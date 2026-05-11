package pd20.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
    private long id;
    private String title;
    private String body;
}
