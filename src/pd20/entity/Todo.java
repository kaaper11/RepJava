package pd20.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Todo {
    private long id;
    private String title;
    private boolean completed;
}
