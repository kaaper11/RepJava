package pd12;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@AllArgsConstructor
public class UserAccount {
    protected Long id;
    protected String email;
    protected String displayName;


}
